package com.example.prutc.peershare_bank.presentation.request

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import android.support.v7.widget.DividerItemDecoration
import com.example.prutc.peershare_bank.R
import com.example.prutc.peershare_bank.data.entity.Recent
import com.example.prutc.peershare_bank.util.toJson


class RequestListActivity : AppCompatActivity() {

    private val promptPays = listOf("0830763040", "0876831209", "0866678800")
    private var index = 0

    private val viewModel by lazy {
        ViewModelProviders.of(this)
            .get(BankViewModel::class.java)
    }

    private var requestPayments = mutableListOf<Recent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        observeView()
        observeData()
        observeError()
        viewModel.list(1)
    }

    private fun initView() {
        srRequestList.setOnRefreshListener {
            viewModel.list(1)
        }
        with(rvRequest) {
            layoutManager = LinearLayoutManager(this@RequestListActivity, LinearLayoutManager.VERTICAL, false)
            adapter = RequestListAdapter(requestPayments=requestPayments,onRequestClick =
            { peers ->
                startActivity<PeerListActivity>("peers" to peers.toJson())
            }, onPeerClick = {})

            val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
//        tvTitle.setOnClickListener {
//            index += 1
//            requestPayments.clear()
//            rvRequest.removeAllViews()
//            viewModel.list(p1)
//            toast(promptPays[index % 3])
//        }
    }

    private fun observeView() {
        viewModel.state.requestPaymentsLoading.observe(this, Observer { isLoading ->
            srRequestList.isRefreshing = isLoading == true
        })
    }

    private fun observeData() {
        viewModel.requestPayments.observe(this, Observer { requestPayments ->
            requestPayments?.let {
                this.requestPayments.clear()
                this.requestPayments.addAll(it)
                rvRequest.adapter?.notifyDataSetChanged()
            }
        })
    }

    private fun observeError() {
        viewModel.errorMessage.observe(this, Observer { error ->
            error?.let {
                toast(it)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.list(1)
    }
}
