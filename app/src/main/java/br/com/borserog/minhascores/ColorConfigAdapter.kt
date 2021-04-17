package br.com.borserog.minhascores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class ColorConfigAdapter(private val context: Context,
                         private val colorsList: ColorsList) :
    BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return colorsList.count()
    }

    override fun getItem(position: Int): Any {
        return colorsList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val colorConfig = this.getItem(position) as ColorConfig;
        val row = convertView ?: inflater.inflate(R.layout.list_item_color, null)

        val titleTextView = row.findViewById(R.id.color_list_title) as TextView
        val subtitleTextView = row.findViewById(R.id.color_list_subtitle) as TextView
        val thumbnailImageView = row.findViewById(R.id.color_thumbnail) as ImageView

        titleTextView.text = colorConfig.nome;
        subtitleTextView.text = colorConfig.toHex();
        thumbnailImageView.setColorFilter(colorConfig.codigo)

        return row
    }
}