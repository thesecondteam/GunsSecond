/**
 * 运单管理管理初始化
 */
var BusiWaybill = {
    id: "BusiWaybillTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BusiWaybill.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '运单号', field: 'waybillid', visible: true, align: 'center', valign: 'middle'},
            {title: '车次', field: 'trainnum', visible: true, align: 'center', valign: 'middle'},
            {title: '开始时间', field: 'starttime', visible: true, align: 'center', valign: 'middle'},
            {title: '结束时间', field: 'endtime', visible: true, align: 'center', valign: 'middle'},
            {title: '集装箱数', field: 'boxnumber', visible: true, align: 'center', valign: 'middle'},
            {title: '起点', field: 'startpoint', visible: true, align: 'center', valign: 'middle'},
            {title: '终点', field: 'endpiont', visible: true, align: 'center', valign: 'middle'},
            {title: '距离', field: 'distance', visible: true, align: 'center', valign: 'middle'},
            {title: '状态码', field: 'statecode', visible: true, align: 'center', valign: 'middle'},
            {title: '装/卸种类', field: 'loadtype', visible: true, align: 'center', valign: 'middle'},
            {title: '装货码', field: 'loadcode', visible: true, align: 'center', valign: 'middle'},
            {title: '卸货码', field: 'unloadcode', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BusiWaybill.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BusiWaybill.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加到达运单管理
 */
BusiWaybill.openAddBusiWaybill = function () {
    var index = layer.open({
        type: 2,
        title: '添加运单管理',
        area: ['1400px', '820px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/busiWaybill/busiWaybill_add'
    });
    this.layerIndex = index;
};

/**
 * 点击生成出发运单管理
 */
BusiWaybill.openCreateBusiWaybill = function () {
    var index = layer.open({
        type: 2,
        title: '添加运单管理',
        area: ['1400px', '820px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/busiWaybill/busiWaybill_create'
    });
    this.layerIndex = index;
};

/**
 * 打开查看运单管理详情
 */
BusiWaybill.openBusiWaybillDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '运单管理详情',
            area: ['1400px', '820px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/busiWaybill/busiWaybill_update/' + BusiWaybill.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除运单管理
 */
BusiWaybill.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/busiWaybill/delete", function (data) {
            Feng.success("删除成功!");
            BusiWaybill.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("busiWaybillId",this.seItem.id);
        ajax.start();
    }
};
/**
 * 结束
 */
BusiWaybill.finish = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/busiWaybill/finish", function (data) {
            Feng.success("运单已结束!");
            BusiWaybill.table.refresh();
        }, function (data) {
            Feng.error("结束异常!" + data.responseJSON.message + "!");
        });
        ajax.set(this.seItem);
        ajax.start();
    }
};

/**
 * 查询运单管理列表
 */
BusiWaybill.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BusiWaybill.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BusiWaybill.initColumn();
    var table = new BSTable(BusiWaybill.id, "/busiWaybill/list", defaultColunms);
    table.setPaginationType("client");
    BusiWaybill.table = table.init();
});
