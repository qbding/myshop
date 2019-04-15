<html>
<head>
    <title>分类列表</title>

    <#--<link rel="stylesheet" href="../static/z-tree/css/demo.css" type="text/css">-->
    <link href="../static/z-tree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
    <script type="text/javascript" src="../static/z-tree/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="../static/z-tree/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="../static/z-tree/js/jquery.ztree.all.js"></script>
    <script type="text/javascript" src="../static/z-tree/js/jquery.ztree.excheck.js"></script>
    <script type="text/javascript" src="../static/z-tree/js/jquery.ztree.excheck.js"></script>
    <script type="text/javascript" src="../static/z-tree/js/jquery.ztree.exedit.js"></script>


    <SCRIPT LANGUAGE="JavaScript">
        var  zTreeObj1;
        // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
        var setting1 = {
            view: {
                selectedMulti: true, //设置是否能够同时选中多个节点
                showIcon: true,  //设置是否显示节点图标
                showLine: true,  //设置是否显示节点与节点之间的连线
                showTitle: true,  //设置是否显示节点的title提示信息
            },
            data: {
                simpleData: {
                    enable: true, //设置是否启用简单数据格式（zTree支持标准数据格式跟简单数据格式，上面例子中是标准数据格式）
                    idKey: "id",  //设置启用简单数据格式时id对应的属性名称
                    pidKey: "propertyId" //设置启用简单数据格式时parentId对应的属性名称,ztree根据id及pid层级关系构建树结构
                }

            },
            check:{
                enable: true   //设置是否显示checkbox复选框
            },
            async: {    //ztree异步请求数据
                enable: true,
                url: "/myShop/category/getPropertyByCategoryId",
                otherParam: {"id":function () {
                        return $("#categoryId").val();
                    }}
            }
        };
        // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）

        $(document).ready(function(){
            zTreeObj1 = $.fn.zTree.init($("#propertyTree"), setting1); //初始化
        });
    </SCRIPT>

    <SCRIPT LANGUAGE="JavaScript">
        var  zTreeObj ;
        // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
        var setting = {
            view: {
                selectedMulti: true, //设置是否能够同时选中多个节点
                showIcon: true,  //设置是否显示节点图标
                showLine: true,  //设置是否显示节点与节点之间的连线
                showTitle: true,  //设置是否显示节点的title提示信息
            },
            data: {
                simpleData: {
                    enable: true, //设置是否启用简单数据格式（zTree支持标准数据格式跟简单数据格式，上面例子中是标准数据格式）
                    idKey: "id",  //设置启用简单数据格式时id对应的属性名称
                    pidKey: "parentId" //设置启用简单数据格式时parentId对应的属性名称,ztree根据id及pid层级关系构建树结构
                }
            },
            check:{
                enable: true   //设置是否显示checkbox复选框
            },
            callback:{
                onClick:zTreeOnClick
            },
            async: {    //ztree异步请求数据
                enable: true,
                url: "/myShop/category/getList"
            }
        };
        // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）


        function zTreeOnClick(event, treeId, treeNode) {
            console.log(treeNode.id)

            $("#categoryId").val(treeNode.id)
            zTreeObj.expandNode(treeNode)//单击节点展开其子节点
            zTreeObj1.reAsyncChildNodes(null, "refresh");
        };


        $(document).ready(function(){
            zTreeObj = $.fn.zTree.init($("#treeDemo"), setting); //初始化
        });
    </SCRIPT>
</head>

<body>
<div class="main-content-inner">
    <div class="breadcrumbs ace-save-state" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="ace-icon fa fa-home home-icon"></i>
                <a href="#">Home</a>
            </li>
            <li class="active">欢迎页</li>
        </ul><!-- /.breadcrumb -->

        <div class="nav-search" id="nav-search">
            <form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="ace-icon fa fa-search nav-search-icon"></i>
								</span>
            </form>
        </div><!-- /.nav-search -->
    </div>

    <div class="page-content">
        <ul id="treeDemo" class="ztree" style="width: 500px;float: left"></ul>
        <input type="hidden" value="" id="categoryId">
        <ul id="propertyTree" class="ztree"  style="width: 500px;float: left"></ul>
    </div>

</div>
</body>
</html>