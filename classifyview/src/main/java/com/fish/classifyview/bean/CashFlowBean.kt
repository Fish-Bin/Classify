package com.fish.classifyview.bean

/**
 * F10  3.0.6 现金流量表最新数据
 *
 * [cfBeforeFin] 融资业务流量净额
 * [cfOperAct] 经营流量现金净额
 * [cfInv] 投资业务流量净额
 */
data class CashFlowBean(
        var cfBeforeFin: Double = 0.0,
        var cfOperAct: Double = 0.0,
        var cfInv: Double = 0.0,
        var unit: String? = null,
        var currency: String? = null,
        var rate: Double = 0.0,
        var yearend: String? = null
)