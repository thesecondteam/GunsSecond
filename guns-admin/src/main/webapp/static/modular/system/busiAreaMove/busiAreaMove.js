/**
 * 场地业务操作模块管理初始化
 */
var BusiAreaMove = {
    id: "BusiAreaMoveTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BusiAreaMove.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '自增ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '原场地', field: 'oldArea', visible: true, align: 'center', valign: 'middle'},
            {title: '目的场地', field: 'newArea', visible: true, align: 'center', valign: 'middle'},
            {title: '操作时间', field: 'operTime', visible: true, align: 'center', valign: 'middle'},
            {title: '操作人', field: 'operPerson', visible: true, align: 'center', valign: 'middle'},
            {title: '箱子编号', field: 'boxId', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BusiAreaMove.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BusiAreaMove.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加场地业务操作模块
 */
BusiAreaMove.openAddBusiAreaMove = function () {
    var index = layer.open({
        type: 2,
        title: '添加场地业务操作模块',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/busiAreaMove/busiAreaMove_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看场地业务操作模块详情
 */
BusiAreaMove.openBusiAreaMoveDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '场地业务操作模块详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/busiAreaMove/busiAreaMove_update/' + BusiAreaMove.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除场地业务操作模块
 */
BusiAreaMove.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/busiAreaMove/delete", function (data) {
            Feng.success("删除成功!");
            BusiAreaMove.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("busiAreaMoveId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询场地业务操作模块列表
 */
BusiAreaMove.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BusiAreaMove.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BusiAreaMove.initColumn();
    var table = new BSTable(BusiAreaMove.id, "/busiAreaMove/list", defaultColunms);
    table.setPaginationType("client");
    BusiAreaMove.table = table.init();
});
