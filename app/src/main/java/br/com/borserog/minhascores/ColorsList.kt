package br.com.borserog.minhascores

class ColorsList {
    private lateinit var colorList: ArrayList<ColorConfig>

    init {
        this.colorList = arrayListOf();
    }

    fun add(colorConfig: ColorConfig) {
        this.colorList.add(colorConfig)
    }

    fun remove(colorConfig: ColorConfig) {
        this.colorList.remove(colorConfig)
    }

    operator fun get(index: Int): ColorConfig {
        return this.colorList[index]
    }

    fun get(): ArrayList<ColorConfig> {
        return this.colorList
    }

    fun count(): Int {
        return this.colorList.count()
    }

}