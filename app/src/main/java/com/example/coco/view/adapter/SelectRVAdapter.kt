package com.example.coco.view.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.coco.R
import com.example.coco.dataModel.CurrentPriceResult
import org.w3c.dom.Text

class SelectRVAdapter(val context: Context, val coinPriceList: List<CurrentPriceResult>)
    : RecyclerView.Adapter<SelectRVAdapter.ViewHolder>(){

    // 하트가 눌린 코인들 리스트 보관 >> 다시 뺴기 위해서
    private val selectedCoinList = ArrayList<String>()

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val coinName : TextView = view.findViewById(R.id.coinName)
        val coinPriceUpDown : TextView = view.findViewById(R.id.coinPriceUpDown)
        val likeImage : ImageView = view.findViewById(R.id.likeBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.intro_coin_item, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.coinName.text = coinPriceList[position].coinName
        val fluctate_24H = coinPriceList[position].coinInfo.fluctate_24H
        if(fluctate_24H.contains("-")){
            holder.coinPriceUpDown.text = fluctate_24H
            holder.coinPriceUpDown.setTextColor(Color.parseColor("#114fed"))
        }else{
            // + 인 경우 앞에 +추가
            val formattedString = context.getString((R.string.fluctate_up), fluctate_24H)
            holder.coinPriceUpDown.text = formattedString
            holder.coinPriceUpDown.setTextColor(Color.parseColor("#ed2e11"))
        }


        // 관심 코인 하트 클릭
        val likeImage = holder.likeImage
        val currentCoin = coinPriceList[position].coinName

        // recycle 재활용하기 때문에 위에서 누른 하트가 아래서도 반복되는걸 막기 위함
        if(selectedCoinList.contains(currentCoin)){
            likeImage.setImageResource((R.drawable.like_red))
        }else{
            likeImage.setImageResource((R.drawable.like_grey))
        }

        // 하트가 눌렸을 때 색 변화
        likeImage.setOnClickListener{

            if(selectedCoinList.contains(currentCoin)){ // 이미 눌린 코인
                selectedCoinList.remove(currentCoin)
                likeImage.setImageResource(R.drawable.like_grey)
            }else{  // 안눌린 코인
                selectedCoinList.add(currentCoin)
                likeImage.setImageResource(R.drawable.like_red)
            }



        }

    }

    override fun getItemCount(): Int {
        return coinPriceList.size

    }

}
