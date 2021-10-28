package uz.lars_lion.testuz.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.ImageRequest
import coil.request.ImageResult
import uz.lars_lion.testuz.databinding.IteCardBinding
import uz.lars_lion.testuz.model.ArticleLocal
import uz.lars_lion.testuz.utils.visible

class CardNewsAdapter(
    private val listener: CardNewsPageAdapterListener
) : RecyclerView.Adapter<CardNewsAdapter.CreatePageViewHolder>() {
    private var dataList = ArrayList<ArticleLocal>()

    private val diffCallBack = object : DiffUtil.ItemCallback<ArticleLocal>() {
        override fun areItemsTheSame(
            oldItem: ArticleLocal,
            newItem: ArticleLocal
        ): Boolean = oldItem.title == newItem.title

        override fun areContentsTheSame(
            oldItem: ArticleLocal,
            newItem: ArticleLocal
        ): Boolean =
            oldItem == newItem
    }
    private val differ = AsyncListDiffer(this, diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CreatePageViewHolder(
            IteCardBinding.inflate(LayoutInflater.from(parent.context)),
            listener
        )

    override fun onBindViewHolder(holder: CreatePageViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount() =
        differ.currentList.size

    fun submitList(list: List<ArticleLocal>) {
        differ.submitList(list)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<ArticleLocal>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }

    inner class CreatePageViewHolder
    constructor(
        private val binding: IteCardBinding,
        private val listener: CardNewsPageAdapterListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ArticleLocal) {
            with(binding) {
                imgCard.load(data.urlToImage){
                    crossfade(true)
                    listener(object : ImageRequest.Listener{
                        override fun onSuccess(
                            request: ImageRequest,
                            metadata: ImageResult.Metadata
                        ) {
                            super.onSuccess(request, metadata)
                            progresCard.visible(false)
                        }
                    })
                }

                tvAuthor.text = data.author
                tvDate.text= data.publishedAt
                tvTitle.text = data.title

                root.setOnClickListener {
                    if (adapterPosition != RecyclerView.NO_POSITION)
                        listener.onItemClickOption(adapterPosition, data)
                }
            }
        }
    }

    interface CardNewsPageAdapterListener {
        fun onItemClickOption(position: Int, data: ArticleLocal)
    }
}