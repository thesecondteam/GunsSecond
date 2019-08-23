/**
 * 业务操作记录管理初始化
 */
var BusiMove = {
    id: "BusiMoveTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BusiMove.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '自增ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '原场地编号', field: 'oldArea', visible: true, align: 'center', valign: 'middle'},
            {title: '目的场地', field: 'newArea', visible: true, align: 'center', valign: 'middle'},
            {title: '箱子编码', field: 'boxCode', visible: true, align: 'center', valign: 'middle'},
            {title: '操作时间', field: 'operTime', visible: true, align: 'center', valign: 'middle'},
            {title: '操作人', field: 'operPerson', visible: true, align: 'center', valign: 'middle'},
            {title: '操作类型', field: 'opeType', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BusiMove.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BusiMove.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加业务操作记录
 */
BusiMove.openAddBusiMove = function () {
    var index = layer.open({
        type: 2,
        title: '添加业务操作记录',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/busiMove/busiMove_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看业务操作记录详情
 */
BusiMove.openBusiMoveDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '业务操作记录详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/busiMove/busiMove_update/' + BusiMove.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除业务操作记录
 */
BusiMove.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/busiMove/delete", function (data) {
            Feng.success("删除成功!");
            BusiMove.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("busiMoveId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询业务操作记录列表
 */
BusiMove.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BusiMove.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BusiMove.initColumn();
    var table = new BSTable(BusiMove.id, "/busiMove/list", defaultColunms);
    table.setPaginationType("client");
    BusiMove.table = table.init();
});
