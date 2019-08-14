/**
 * 管理初始化
 */
var Area = {
    id: "AreaTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Area.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '场位编码', field: 'areacode', visible: true, align: 'center', valign: 'middle'},
            {title: '场位类型', field: 'areatype', visible: true, align: 'center', valign: 'middle'},
            {title: '状态码', field: 'stateName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Area.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Area.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
Area.openAddArea = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/area/area_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
Area.openAreaDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/area/area_update/' + Area.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
Area.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/area/delete", function (data) {
            Feng.success("删除成功!");
            Area.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("areaId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 启用
 */
Area.enable = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/area/enable", function (data) {
            Feng.success("启用成功!");
            Area.table.refresh();
        }, function (data) {
            Feng.error("启用失败!" + data.responseJSON.message + "!");
        });
        ajax.set(this.seItem);
        ajax.start();
    }
};

/**
 * 禁用
 */
Area.disable = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/area/disable", function (data) {
            Feng.success("禁用成功!");
            Area.table.refresh();
        }, function (data) {
            Feng.error("禁用失败!" + data.responseJSON.message + "!");
        });
        ajax.set(this.seItem);
        ajax.start();
    }
};


/**
 * 查询列表
 */
Area.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Area.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Area.initColumn();
    var table = new BSTable(Area.id, "/area/list", defaultColunms);
    table.setPaginationType("client");
    Area.table = table.init();
});
