package com.example.a3_recyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a3_recyclerview.databinding.ItemUserBinding
import com.example.a3_recyclerview.model.User

interface UserActionListener {
    fun onUserLike(user: User)
}

class UsersAdapter(
    private val actionListener: UserActionListener,
    private val context: Context
) : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>(), View.OnClickListener {

    private var userList = emptyList<User>()

    override fun onClick(v: View) {
        val user = v.tag as User
        when (v.id) {
            R.id.likeImageViewButton -> {
                actionListener.onUserLike(user)
            }
        }
    }

    class UsersViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        binding.likeImageViewButton.setOnClickListener(this)

        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = userList[position]
        with(holder.binding) {
            holder.itemView.tag = user
            holder.itemView.setOnClickListener {
                val dTitle: String = user.fio
                val dDesc: String = user.desc
                val dImage: Int = user.id
                val dYears: String = user.live_years
                val dSex: String = user.sex

                val intent = Intent(context, DetailActivity::class.java)

                intent.putExtra("iTitle", dTitle)
                intent.putExtra("iDesc", dDesc)
                intent.putExtra("iImageView", dImage)
                intent.putExtra("iYears", dYears)
                intent.putExtra("iSex", dSex)
                context.startActivity(intent)
            }
            likeImageViewButton.tag = user

            userNameTextView.text = user.fio
            descTextView.text = user.desc
            sexUserTextView.text = user.sex
            liveYearsTextView.text = user.live_years
            photoImageView.setImageResource(user.id)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<User>) {
        userList = list
        notifyDataSetChanged()
    }
}