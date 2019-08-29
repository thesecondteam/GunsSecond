/**
 * order管理初始化
 */
var BusiOrder = {
    id: "BusiOrderTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BusiOrder.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            // {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '订单编号', field: 'ordernumber', visible: true, align: 'center', valign: 'middle'},
            {title: '运输方式', field: 'transName', visible: true, align: 'center', valign: 'middle'},
            {title: '货物类型', field: 'goodstypeName', visible: true, align: 'center', valign: 'middle'},
            {title: '货物名称', field: 'goodsname', visible: true, align: 'center', valign: 'middle'},
            {title: '货物容量(单位：吨)', field: 'goodsvolume', visible: true, align: 'center', valign: 'middle'},
            //{title: '起点', field: 'startpoint', visible: true, align: 'center', valign: 'middle'},
            {title: '收货方电话', field: 'recephone', visible: true, align: 'center', valign: 'middle'},
           {title: '收货方', field: 'receiver', visible: true, align: 'center', valign: 'middle'},
            {title: '终点', field: 'endpointName', visible: true, align: 'center', valign: 'middle'},
             {title: '发货方电话', field: 'consiphone', visible: true, align: 'center', valign: 'middle'},
            {title: '发货方', field: 'consignor', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'creationtime', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'stateName', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'note', visible: true, align: 'center', valign: 'middle'},
            // {title: '', field: 'spare', visible: true, align: 'center', valign: 'middle'},
            // {title: '', field: 'spare1', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BusiOrder.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BusiOrder.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加order
 */
BusiOrder.openAddBusiOrder = function () {
    var index = layer.open({
        type: 2,
        title: '添加order',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/busiOrder/busiOrder_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看order详情
 */
BusiOrder.openBusiOrderDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: 'order详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/busiOrder/busiOrder_update/' + BusiOrder.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除order
 */
BusiOrder.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/busiOrder/delete", function (data) {
            Feng.success("删除成功!");
            BusiOrder.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("busiOrderId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询order列表
 */
BusiOrder.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BusiOrder.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BusiOrder.initColumn();
    var table = new BSTable(BusiOrder.id, "/busiOrder/list", defaultColunms);
    table.setPaginationType("client");
    BusiOrder.table = table.init();
});

//
// /*生成guid码，订单号*/
// function newGuid()
// {
//     var guid = "";
//     for (var i = 1; i <= 32; i++){
//         var n = Math.floor(Math.random()*16.0).toString(16);
//         guid +=   n;
//         if((i==8)||(i==12)||(i==16)||(i==20))
//             guid += "-";
//     }
//     return guid;
// }
/**
 * 禁用
 */
BusiOrder.disable = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/busiOrder/disable", function (data) {
            Feng.success("禁用成功!");
            BusiOrder.table.refresh();
        }, function (data) {
            Feng.error("禁用失败!" + data.responseJSON.message + "!");
        });
        ajax.set(this.seItem);
        ajax.start();
    }
};

/**
 * 启用
 */
BusiOrder.enable = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/busiOrder/enable", function (data) {
            Feng.success("启用成功!");
            BusiOrder.table.refresh();
        }, function (data) {
            Feng.error("启用失败!" + data.responseJSON.message + "!");
        });
        ajax.set(this.seItem);
        ajax.start();
    }
};
