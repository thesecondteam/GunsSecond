/**
 * 港口管理初始化
 */
var Harbour = {
    id: "HarbourTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Harbour.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '港口号', field: 'harbourcode', visible: true, align: 'center', valign: 'middle'},
            {title: '港口名称', field: 'harbourname', visible: true, align: 'center', valign: 'middle'},
            {title: '国家', field: 'country', visible: true, align: 'center', valign: 'middle'},
            {title: '省份', field: 'province', visible: true, align: 'center', valign: 'middle'},
            {title: '城市', field: 'city', visible: true, align: 'center', valign: 'middle'},
            {title: '轮船数量', field: 'shipnum', visible: true, align: 'center', valign: 'middle'},
            {title: '场地容量', field: 'volume', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'stateName', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '', field: 'spare1', visible: false, align: 'center', valign: 'middle'},
            {title: '', field: 'spare2', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Harbour.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Harbour.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加港口
 */
Harbour.openAddHarbour = function () {
    var index = layer.open({
        type: 2,
        title: '添加港口',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/harbour/harbour_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看港口详情
 */
Harbour.openHarbourDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '港口详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/harbour/harbour_update/' + Harbour.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除港口
 */
Harbour.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/harbour/delete", function (data) {
            Feng.success("删除成功!");
            Harbour.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("harbourId",this.seItem.id);
        ajax.start();
    }
};
/**
 * 禁用
 */
Harbour.disable = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/harbour/disable", function (data) {
            Feng.success("禁用成功!");
            Harbour.table.refresh();
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
Harbour.enable = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/harbour/enable", function (data) {
            Feng.success("启用成功!");
            Harbour.table.refresh();
        }, function (data) {
            Feng.error("启用失败!" + data.responseJSON.message + "!");
        });
        ajax.set(this.seItem);
        ajax.start();
    }
};

/**
 * 查询港口列表
 */
Harbour.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Harbour.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Harbour.initColumn();
    var table = new BSTable(Harbour.id, "/harbour/list", defaultColunms);
    table.setPaginationType("client");
    Harbour.table = table.init();
});
