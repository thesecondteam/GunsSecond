/**
 * 管理初始化
 */
var Updown = {
    id: "UpdownTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Updown.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '订单编号', field: 'ordernumber', visible: true, align: 'center', valign: 'middle'},
            {title: '操作类型', field: 'optype', visible: true, align: 'center', valign: 'middle'},
            {title: '装箱时间', field: 'optime', visible: true, align: 'center', valign: 'middle'},
            {title: '装箱人员', field: 'oppeople', visible: true, align: 'center', valign: 'middle'},
            {title: '接箱场地', field: 'areaid', visible: true, align: 'center', valign: 'middle'},
            {title: '接货人', field: 'recpeople', visible: true, align: 'center', valign: 'middle'},
            {title: '联系方式', field: 'recphone', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Updown.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Updown.seItem = selected[0];
        return true;
    }
};

/**
 * 拼单操作
 */
Updown.pload = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/updown/updown_pload'
    });
    this.layerIndex = index;
};
/**
 * 拆单操作
 */
Updown.cload = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/updown/updown_cload'
    });
    this.layerIndex = index;
};
/**
 * 整单操作
 */
Updown.load = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/updown/updown_load'
    });
    this.layerIndex = index;
};
/**
 * 打开查看详情
 */
Updown.openUpdownDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/updown/updown_update/' + Updown.seItem.ordernumber
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
Updown.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/updown/delete", function (data) {
            Feng.success("删除成功!");
            Updown.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("updownId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
Updown.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Updown.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Updown.initColumn();
    var table = new BSTable(Updown.id, "/updown/list", defaultColunms);
    table.setPaginationType("client");
    Updown.table = table.init();
});
