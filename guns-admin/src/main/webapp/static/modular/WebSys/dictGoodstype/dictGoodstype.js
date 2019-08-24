/**
 * goodstype管理初始化
 */
var DictGoodstype = {
    id: "DictGoodstypeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
DictGoodstype.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            //{title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '货物类型', field: 'goodstype', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'stateName', visible: true, align: 'center', valign: 'middle'},
            // {title: '', field: 'spare', visible: true, align: 'center', valign: 'middle'},
            // {title: '', field: 'spare1', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
DictGoodstype.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        DictGoodstype.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加goodstype
 */
DictGoodstype.openAddDictGoodstype = function () {
    var index = layer.open({
        type: 2,
        title: '添加货物类型',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/dictGoodstype/dictGoodstype_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看goodstype详情
 */
DictGoodstype.openDictGoodstypeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '货物类型修改',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/dictGoodstype/dictGoodstype_update/' + DictGoodstype.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除goodstype
 */
DictGoodstype.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/dictGoodstype/delete", function (data) {
            Feng.success("删除成功!");
            DictGoodstype.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("dictGoodstypeId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询goodstype列表
 */
DictGoodstype.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    DictGoodstype.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = DictGoodstype.initColumn();
    var table = new BSTable(DictGoodstype.id, "/dictGoodstype/list", defaultColunms);
    table.setPaginationType("client");
    DictGoodstype.table = table.init();
});

/**
 * 禁用
 */
DictGoodstype.disable = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/dictGoodstype/disable", function (data) {
            Feng.success("禁用成功!");
            DictGoodstype.table.refresh();
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
DictGoodstype.enable = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/dictGoodstype/enable", function (data) {
            Feng.success("启用成功!");
            DictGoodstype.table.refresh();
        }, function (data) {
            Feng.error("启用失败!" + data.responseJSON.message + "!");
        });
        ajax.set(this.seItem);
        ajax.start();
    }
};
