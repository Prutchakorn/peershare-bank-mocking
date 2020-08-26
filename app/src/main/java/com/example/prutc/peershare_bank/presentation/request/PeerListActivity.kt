package com.example.prutc.peershare_bank.presentation.request

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.example.prutc.peershare_bank.R
import com.example.prutc.peershare_bank.data.entity.Peer
import com.example.prutc.peershare_bank.util.toObjects
import kotlinx.android.synthetic.main.activity_peer_list.*
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.longToast

class PeerListActivity : AppCompatActivity() {

    private val peers by lazy {
//        intent.getParcelableExtra<RequestPayment>("requestPayment")
        intent.getStringExtra("peers").toObjects(Array<Peer>::class.java)
    }
    private val viewModel by lazy {
        ViewModelProviders.of(this)
            .get(BankViewModel::class.java)
    }

    private val loadingDialog by lazy {
        indeterminateProgressDialog("Loading...") { }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peer_list)

        initView()
        observeView()
        observeData()
    }


    private fun initView() {
        with(rvPeers) {
            layoutManager = LinearLayoutManager(this@PeerListActivity, LinearLayoutManager.VERTICAL, false)

            adapter = RequestListAdapter(peers=peers, requestPayments = emptyList(),    onPeerClick = {
                viewModel.transfer(it)
            }, onRequestClick = {})

            val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }

        btnPay.setOnClickListener {
            var rtp = etRTP.text.toString()
            if (rtp.isNotEmpty()) {
                var rtpId = rtp.toInt()
                viewModel.pay(rtpId)
            }

        }

        rvPeers.adapter?.notifyDataSetChanged()
    }

    private fun observeView() {
        viewModel.state.requestPaymentsLoading.observe(this, Observer { isLoading ->
            if (isLoading == true) {
                loadingDialog.show()
            } else {
                loadingDialog.dismiss()
            }
        })
    }

    private fun observeData() {
        viewModel.status.observe(this, Observer { status ->
            status?.let {
                    longToast("send$it")
                    finish()
            }
        })
    }
}
