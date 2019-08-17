/**
 * 管理初始化
 */
var Box = {
    id: "BoxTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Box.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '订单编号', field: 'ordernumber', visible: true, align: 'center', valign: 'middle'},
            {title: '运输方式', field: 'trantype', visible: true, align: 'center', valign: 'middle'},
            {title: '集装箱编码', field: 'boxcode', visible: true, align: 'center', valign: 'middle'},
            {title: '场地', field: 'areaid', visible: true, align: 'center', valign: 'middle'},
            {title: '添加时间', field: 'addtime', visible: true, align: 'center', valign: 'middle'},
            {title: '箱子类型', field: 'boxtype', visible: true, align: 'center', valign: 'middle'},
            {title: '箱子尺寸', field: 'boxsize', visible: true, align: 'center', valign: 'middle'},
            {title: '货物类型', field: 'goodstype', visible: true, align: 'center', valign: 'middle'},
            {title: '货物名称', field: 'goodsname', visible: true, align: 'center', valign: 'middle'},
            {title: '起点', field: 'startpointName', visible: true, align: 'center', valign: 'middle'},
            {title: '终点', field: 'endpointName', visible: true, align: 'center', valign: 'middle'},
            {title: '是否空箱', field: 'emptycode', visible: true, align: 'center', valign: 'middle'},
            {title: '状态码', field: 'stateName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Box.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Box.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
Box.openAddBox = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/box/box_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
Box.openBoxDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/box/box_update/' + Box.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
Box.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/box/delete", function (data) {
            Feng.success("删除成功!");
            Box.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("boxId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
Box.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Box.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Box.initColumn();
    var table = new BSTable(Box.id, "/box/list", defaultColunms);
    table.setPaginationType("client");
    Box.table = table.init();
});
