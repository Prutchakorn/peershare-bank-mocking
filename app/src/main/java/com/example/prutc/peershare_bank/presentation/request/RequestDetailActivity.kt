//package com.example.prutc.peershare_bank.presentation.request
//
//import android.arch.lifecycle.Observer
//import android.arch.lifecycle.ViewModelProviders
//import android.support.v7.app.AppCompatActivity
//import android.os.Bundle
//import com.example.prutc.peershare_bank.R
//import com.example.prutc.peershare_bank.data.entity.RequestPayment
//import com.example.prutc.peershare_bank.extension.toDate
//import com.example.prutc.peershare_bank.extension.toDateTime
//import kotlinx.android.synthetic.main.activity_request_detail.*
//import org.jetbrains.anko.indeterminateProgressDialog
//import org.jetbrains.anko.longToast
//
//class RequestDetailActivity : AppCompatActivity() {
//
//    private val requestPayment by lazy {
//        intent.getParcelableExtra<RequestPayment>("requestPayment")
//    }
//
//    private val viewModel by lazy {
//        ViewModelProviders.of(this)
//            .get(BankViewModel::class.java)
//    }
//
//    private val loadingDialog by lazy {
//        indeterminateProgressDialog("Loading...") { }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_request_detail)
//
//        initView()
//        observeView()
//        observeData()
//    }
//
//    private fun initView() {
//        with(requestPayment) {
//            tvRequesterName.text = SenderPromptPay
//            tvRequesterPromptPay.text = SenderPromptPay
//            tvPayerName.text = ReceiverPromptPay
//            tvPayerPromptPay.text = ReceiverPromptPay
//            tvAmount.text = resources.getString(R.string.request_detail_amount, Amount)
//            tvDueDate.text = RequestedDateTime.toDate()
//            tvTransactionDate.text = RequestedDateTime.toDateTime()
//
//            if (this.IsActive) {
//                tvStatus.text= "Waiting"
//            }
//            tvTransfer.setOnClickListener {
//                viewModel.transfer(Id, true)
//            }
//        }
//    }
//
//    private fun observeView() {
//        viewModel.state.requestPaymentsLoading.observe(this, Observer { isLoading ->
//            if (isLoading == true) {
//                loadingDialog.show()
//            } else {
//                loadingDialog.dismiss()
//            }
//        })
//    }
//
//    private fun observeData() {
//        viewModel.status.observe(this, Observer { status ->
//            status?.let {
//               if (it == "\"Payment Successful\"")  {
//                   longToast(it)
//                   finish()
//               }
//            }
//        })
//    }
//}
