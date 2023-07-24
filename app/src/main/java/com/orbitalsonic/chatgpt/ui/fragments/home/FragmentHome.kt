package com.orbitalsonic.chatgpt.ui.fragments.home

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orbitalsonic.chatgpt.R
import com.orbitalsonic.chatgpt.databinding.FragmentHomeBinding
import com.orbitalsonic.chatgpt.helpers.adapters.recyclerView.AdapterChatAi
import com.orbitalsonic.chatgpt.helpers.datamodel.ChatMessage
import com.orbitalsonic.chatgpt.helpers.interfaces.OnChatItemClickListener
import com.orbitalsonic.chatgpt.helpers.listeners.DebounceListener.setDebounceClickListener
import com.orbitalsonic.chatgpt.helpers.utils.SettingUtils.copyClipboardData
import com.orbitalsonic.chatgpt.roomdb.tables.ChatTable
import com.orbitalsonic.chatgpt.ui.fragments.base.BaseFragment

class FragmentHome : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home),
    OnChatItemClickListener {

    private val adapterChatAi by lazy { AdapterChatAi(this) }

    override fun onViewCreatedOneTime() {
        clickListeners()
        initValues()
        initRecyclerView()
    }

    override fun onViewCreatedEverytime() {
        initViewModel()
    }

    private fun initValues() {
        binding.sendEnabled = false
        binding.micEnabled = true
    }

    private fun clickListeners() {
        binding.btnSend.setDebounceClickListener {
            if (diComponent.internetManager.isInternetConnected) {
                if (validateText()) {
                    val message = binding.etInput.text.toString()
                    val chatMessages = listOf(
//                        ChatMessage(role = "system", content = "Reply all chats only in urdu language"),
                        ChatMessage(role = "user", content = message)
                    )
                    addToChat("user", message)
                    sendChatRequest(chatMessages)
                    hideKeyboard()
                } else {
                    showToast("Message can't be empty")
                }
            } else {
                showToast("No Internet!")
            }
        }

        binding.btnMic.setDebounceClickListener {
            showToast("Coming Soon Feature!")
        }

        binding.etInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.sendEnabled = count > 0
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun initRecyclerView() {
        binding.chatRecyclerview.adapter = adapterChatAi
    }

    private fun initViewModel() {
        diComponent.aiChatViewModel.allChatList.observe(viewLifecycleOwner) {
            adapterChatAi.submitList(it)
            withDelay(500) {
                if (adapterChatAi.itemCount > 0) {
                    binding.chatRecyclerview.smoothScrollToPosition(adapterChatAi.itemCount - 1)
                }
            }
        }
    }

    private fun addToChat(mRole: String, mContent: String) {
        val check = mRole == "user"
        diComponent.aiChatViewModel.insertChat(
            ChatTable(
                roleCheck = check,
                role = mRole,
                content = mContent
            )
        )
    }

    private fun sendChatRequest(chatMessages: List<ChatMessage>) {
        binding.sendEnabled = false
        binding.micEnabled = false
        binding.etInput.setText("")
        binding.lavChatLoading.visibility = View.VISIBLE

        diComponent.openApiClient.getChatCompletion(chatMessages) { response, error ->
            binding.micEnabled = true
            binding.lavChatLoading.visibility = View.GONE
            if (error != null) {
                Log.e("ChatGPTTAG", "getChatCompletion: ${error.message}")
            } else {
                response?.let { result ->
                    // Process the chat completion response
                    val completion = result.choices[0].message
                    val reply = completion.content
                    processReply(reply)
                }
            }
        }
    }

    private fun processReply(reply: String) {
        Log.d("ChatGPTTAG", "processReply: $reply")
        addToChat("assistant", reply)
    }

    private fun validateText() = binding.etInput.text.isNotEmpty()

    override fun onItemCopy(chatTable: ChatTable) {
        activity.copyClipboardData(chatTable.content)
        showToast("Content Copied!")
    }

    override fun navIconBackPressed() {
        onBackPressed()
    }

    override fun onBackPressed() {
        mainActivity.homeBackPressed()
    }


}