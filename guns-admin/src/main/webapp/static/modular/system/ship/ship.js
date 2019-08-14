/**
 * 船舶管理初始化
 */
var Ship = {
    id: "ShipTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Ship.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '船舶IMO编号', field: 'imo', visible: true, align: 'center', valign: 'middle'},
            {title: '船舶中文名', field: 'shipcname', visible: true, align: 'center', valign: 'middle'},
            {title: '船舶英文名', field: 'shipename', visible: true, align: 'center', valign: 'middle'},
            {title: '所属国', field: 'country', visible: true, align: 'center', valign: 'middle'},
            {title: '长度', field: 'shiplength', visible: true, align: 'center', valign: 'middle'},
            {title: '宽度', field: 'shipwidth', visible: true, align: 'center', valign: 'middle'},
            {title: '载重吨位', field: 'tonnage', visible: true, align: 'center', valign: 'middle'},
            {title: 'MMSI', field: 'mmsi', visible: true, align: 'center', valign: 'middle'},
            {title: '联系人', field: 'contacts', visible: true, align: 'center', valign: 'middle'},
            {title: '电话', field: 'telphone', visible: true, align: 'center', valign: 'middle'},
            {title: '状态码', field: 'stateName', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '', field: 'spare1', visible: false, align: 'center', valign: 'middle'},
            {title: '', field: 'spare2', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Ship.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Ship.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加船舶
 */
Ship.openAddShip = function () {
    var index = layer.open({
        type: 2,
        title: '添加船舶',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/ship/ship_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看船舶详情
 */
Ship.openShipDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '船舶详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/ship/ship_update/' + Ship.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除船舶
 */
Ship.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/ship/delete", function (data) {
            Feng.success("删除成功!");
            Ship.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("shipId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 禁用
 */
Ship.disable = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/ship/disable", function (data) {
            Feng.success("禁用成功!");
            Ship.table.refresh();
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
Ship.enable = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/ship/enable", function (data) {
            Feng.success("启用成功!");
            Ship.table.refresh();
        }, function (data) {
            Feng.error("启用失败!" + data.responseJSON.message + "!");
        });
        ajax.set(this.seItem);
        ajax.start();
    }
};

/**
 * 查询船舶列表
 */
Ship.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Ship.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Ship.initColumn();
    var table = new BSTable(Ship.id, "/ship/list", defaultColunms);
    table.setPaginationType("client");
    Ship.table = table.init();
});
