/**
 * 站点管理初始化
 */
var DictStation = {
    id: "DictStationTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
DictStation.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '站点号', field: 'stationid', visible: true, align: 'center', valign: 'middle'},
        {title: '站点名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '省份', field: 'province', visible: true, align: 'center', valign: 'middle'},
            {title: '状态码', field: 'stateName', visible: true, align: 'center', valign: 'middle'}

    ];
};

/**
 * 检查是否选中
 */
DictStation.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        DictStation.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加站点
 */
DictStation.openAddDictStation = function () {
    var index = layer.open({
        type: 2,
        title: '添加站点',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/dictStation/dictStation_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看站点详情
 */
DictStation.openDictStationDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '站点详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/dictStation/dictStation_update/' + DictStation.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除站点
 */
DictStation.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/dictStation/delete", function (data) {
            Feng.success("删除成功!");
            DictStation.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("dictStationId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 点击禁用
 */
DictStation.disable = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/dictStation/disable", function (data) {
            Feng.success("禁用成功!");
            DictStation.table.refresh();
        }, function (data) {
            Feng.error("禁用失败!" + data.responseJSON.message + "!");
        });
        ajax.set(this.seItem);
        ajax.start();
    }
};
/**
 * 点击启用
 */
DictStation.enable = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/dictStation/enable", function (data) {
            Feng.success("启用成功!");
            DictStation.table.refresh();
        }, function (data) {
            Feng.error("启用失败!" + data.responseJSON.message + "!");
        });
        ajax.set(this.seItem);
        ajax.start();
    }
};
/**
 * 查询站点列表
 */
DictStation.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    DictStation.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = DictStation.initColumn();
    var table = new BSTable(DictStation.id, "/dictStation/list", defaultColunms);
    table.setPaginationType("client");
    DictStation.table = table.init();
});
