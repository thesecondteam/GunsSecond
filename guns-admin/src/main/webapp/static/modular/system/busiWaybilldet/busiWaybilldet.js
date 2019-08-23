/**
 * 运单详情管理管理初始化
 */

var BusiWaybilldet = {
    id: "BusiWaybilldetTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BusiWaybilldet.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '运单详情号', field: 'waybilldetid', visible: true, align: 'center', valign: 'middle'},
            {title: '运单号', field: 'waybillid', visible: true, align: 'center', valign: 'middle'},
            {title: '集装箱号', field: 'boxid', visible: true, align: 'center', valign: 'middle'},
            {title: '集装箱类型', field: 'boxtype', visible: true, align: 'center', valign: 'middle'},
            {title: '集装箱大小', field: 'boxsize', visible: true, align: 'center', valign: 'middle'},
            {title: '集装箱物品', field: 'boxgoods', visible: true, align: 'center', valign: 'middle'},
            {title: '操作', field: 'operation', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createtime', visible: true, align: 'center', valign: 'middle'},
            {title: '状态码', field: 'statecode', visible: true, align: 'center', valign: 'middle'},
            {title: '完成状态', field: 'finish', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BusiWaybilldet.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BusiWaybilldet.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加运单详情管理
 */
BusiWaybilldet.openAddBusiWaybilldet = function () {
    var index = layer.open({
        type: 2,
        title: '添加运单详情管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/busiWaybilldet/busiWaybilldet_add'
    });
    this.layerIndex = index;
};

/**
 * 点击生成运单详情管理
 */
BusiWaybilldet.openCreateBusiWaybilldet = function () {
    var index = layer.open({
        type: 2,
        title: '添加运单详情管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/busiWaybilldet/busiWaybilldet_create'
    });
    this.layerIndex = index;
};

/**
 * 打开查看运单详情管理详情
 */
BusiWaybilldet.openBusiWaybilldetDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '运单详情管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/busiWaybilldet/busiWaybilldet_update/' + BusiWaybilldet.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除运单详情管理
 */
BusiWaybilldet.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/busiWaybilldet/delete", function (data) {
            Feng.success("删除成功!");
            BusiWaybilldet.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("busiWaybilldetId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询运单详情管理列表
 */
BusiWaybilldet.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BusiWaybilldet.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BusiWaybilldet.initColumn();
    var table = new BSTable(BusiWaybilldet.id, "/busiWaybilldet/list", defaultColunms);
    table.setPaginationType("client");
    BusiWaybilldet.table = table.init();
});
