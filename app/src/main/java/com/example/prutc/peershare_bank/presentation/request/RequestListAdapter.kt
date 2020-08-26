package com.example.prutc.peershare_bank.presentation.request

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.prutc.peershare_bank.R
import com.example.prutc.peershare_bank.data.entity.Peer
import com.example.prutc.peershare_bank.data.entity.Recent
import com.example.prutc.peershare_bank.data.entity.RequestPayment
import kotlinx.android.synthetic.main.view_request.view.*

class RequestListAdapter(private val requestPayments: List<Recent> = emptyList(),
                         private val onRequestClick: (List<Peer>) -> Unit,
                         private val onPeerClick: (Int) -> Unit,
                         private val peers: List<Peer> = emptyList()) :
    RecyclerView.Adapter<RequestListAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_request, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = holder.view
        with(view) {
            if (peers.isEmpty()) {
                val requestPayment = requestPayments[position]
                tvFrom.text = requestPayment.Owner.FirstName
                tvFromPromptPay.text = requestPayment.Info.TotalPrice.toString()
                setOnClickListener {
                    onRequestClick(requestPayment.Info.Peers)
                }
            } else {
                val peer = peers[position]
                tvFrom.text = peer.PersonalPrice.toString()
                setOnClickListener {
                    onPeerClick(peer.Id)
                }
            }
//            tvFrom.text = resources.getString(R.string.request_list_from, requestPayment.SenderPromptPay)
//            tvFromPromptPay.text = resources.getString(R.string.request_list_from_prompt_pay, requestPayment.SenderPromptPay)
//            tvToPromptPay.text = resources.getString(R.string.request_list_to_prompt_pay, requestPayment.ReceiverPromptPay)
//            tvDate.text = resources.getString(R.string.request_list_date, requestPayment.RequestedDateTime.toDate())
//            tvAmount.text = resources.getString(R.string.request_list_amount, requestPayment.Amount)
        }
    }

    override fun getItemCount() = requestPayments.size
}