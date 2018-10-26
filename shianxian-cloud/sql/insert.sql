-- 系统设置
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (1, '系统设置', 'system:*', '0', 'menu', null, null);
-- 用户
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (2, '用户管理', 'system:user:*', '1', 'menu', '/user/*', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (3, '用户添加', 'system:user:save', '2', 'button', '/user/saveUser' , null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (4, '用户修改', 'system:user:update', '2', 'button', '/user/updateUserById', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (5, '用户删除', 'system:user:delete', '2', 'button', '/user/deleteUser', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (6, '用户查询', 'system:user:select', '2', 'button', '/user/selectUserByPage', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (7, '修改密码', 'system:user:updatePwd', '2', 'button', '/user/updatePassword', '修改密码');
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (8, '用户绑定角色', 'system:user:binding', '2', 'button', '/userRole/*', '用户绑定角色');
-- 角色
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (9, '角色管理', 'system:role:*', '1', 'menu', '/role/*', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (10, '角色添加', 'system:role:save', '9', 'button', '/role/saveOrUpdateRole' , null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (11, '角色修改', 'system:role:update', '9', 'button', '/role/saveOrUpdateRole', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (12, '角色删除', 'system:role:delete', '9', 'button', '/role/deleteRole', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (13, '角色查询', 'system:role:select', '9', 'button', '/role/selectRoleByPage', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (14, '设置权限', 'system:role:binding', '9', 'button', '/rolePermission/*', '角色绑定权限');
-- 企业管理
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (15, '企业管理', 'system:company:*', '1', 'menu', '/company/*', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (16, '企业添加', 'system:company:save', '15', 'button', '/company/saveOrUpdateCompany' , null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (17, '企业修改', 'system:company:update', '15', 'button', '/company/saveOrUpdateCompany*', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (18, '企业删除', 'system:company:delete', '15', 'button', '/company/deleteCompany', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (19, '企业查询', 'system:company:select', '15', 'button', '/company/selectByUserId', null);
-- ===================================================================================================================================================================================================
-- 基础资料
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (20, '基础资料', 'base:*', '0', 'menu', null, null);
-- 物料管理
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (21, '物料管理', 'base:material:*', '20', 'menu', '/material/*', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (22, '物料管理添加', 'base:material:save', '21', 'button', '/material/saveOrUpdateMaterial' , null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (23, '物料管理修改', 'base:material:update', '21', 'button', '/material/saveOrUpdateMaterial', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (24, '物料管理删除', 'base:material:delete', '21', 'button', '/material/deleteMaterial', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (25, '物料管理查询', 'base:material:select', '21', 'button', '/material/selectMaterialByPage', null);
-- 类别管理
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (26, '类别管理', 'base:category:*', '20', 'menu', '/category/*', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (27, '类别管理添加', 'base:category:save', '26', 'button', '/category/saveOrUpdateCategory' , null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (28, '类别管理修改', 'base:category:update', '26', 'button', '/category/saveOrUpdateCategory', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (29, '类别管理删除', 'base:category:delete', '26', 'button', '/category/deleteCategory', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (30, '类别管理查询', 'base:category:select', '26', 'button', '/category/selectCategoryByPage', null);
-- 计量单位
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (31, '计量单位', 'base:unit:*', '20', 'menu', '/unit/*', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (32, '计量单位添加', 'base:unit:save', '31', 'button', '/unit/saveOrUpdateUnit' , null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (33, '计量单位修改', 'base:unit:update', '31', 'button', '/unit/saveOrUpdateUnit', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (34, '计量单位删除', 'base:unit:delete', '31', 'button', '/unit/deleteUnit', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (35, '计量单位查询', 'base:unit:select', '31', 'button', '/unit/selectUnitByPage', null);
-- 供应商管理
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (36, '供应商管理', 'base:supplier:*', '20', 'menu', '/supplier/*', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (37, '供应商添加', 'base:supplier:save', '36', 'button', '/supplier/saveOrUpdateSupplier' , null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (38, '供应商修改', 'base:supplier:update', '36', 'button', '/supplier/saveOrUpdateSupplier', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (39, '供应商删除', 'base:supplier:delete', '36', 'button', '/supplier/deleteSupplier', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (40, '供应商查询', 'base:supplier:select', '36', 'button', '/supplier/selectSupplierByPage', null);
-- 客户管理
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (41, '客户管理', 'base:customer:*', '20', 'menu', '/customer/*', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (42, '客户添加', 'base:customer:save', '41', 'button', '/customer/saveOrUpdateCustomer' , null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (43, '客户修改', 'base:customer:update', '41', 'button', '/customer/saveOrUpdateCustomer', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (44, '客户删除', 'base:customer:delete', '41', 'button', '/customer/deleteCustomer', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (45, '客户查询', 'base:customer:select', '41', 'button', '/customer/selectCustomerByPage', null);
-- 加工模板管理
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (46, '加工模板管理', 'base:processTemplate:*', '20', 'menu', '/processTemplate/*', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (47, '加工模板添加', 'base:processTemplate:save', '46', 'button', '/processTemplate/saveOrUpdateProcessTemplate' , null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (48, '加工模板修改', 'base:processTemplate:update', '46', 'button', '/processTemplate/saveOrUpdateProcessTemplate', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (49, '加工模板删除', 'base:processTemplate:delete', '46', 'button', '/processTemplate/deleteProcessTemplate', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (50, '加工模板查询', 'base:processTemplate:select', '46', 'button', '/processTemplate/selectProcessTemplateByPage', null);
-- ================================================================================================================================================================================================
-- 流通管理
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (51, '流通管理', 'flow:*', '0', 'menu', null, null);
-- 采购入库单
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (52, '采购入库单', 'flow:purchaseInOrder:*', '51', 'menu', '/purchaseInOrder/*', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (53, '加采购入库添加', 'flow:purchaseInOrder:save', '52', 'button', '/purchaseInOrder/saveOrUpdatePurchaseInOrder' , null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (54, '采购入库修改', 'flow:purchaseInOrder:update', '52', 'button', '/purchaseInOrder/saveOrUpdatePurchaseInOrder', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (55, '采购入库删除', 'flow:purchaseInOrder:delete', '52', 'button', '/purchaseInOrder/deletePurchaseInOrder', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (56, '采购入库查询', 'flow:purchaseInOrder:select', '52', 'button', '/purchaseInOrder/selectPurchaseInOrderByPage', null);
-- 销售单
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (57, '销售单', 'flow:saleOrder:*', '51', 'menu', '/saleOrder/*', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (58, '销售单添加', 'flow:saleOrder:save', '57', 'button', '/saleOrder/saveOrUpdateSaleOrder' , null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (59, '销售单修改', 'flow:saleOrder:update', '57', 'button', '/saleOrder/saveOrUpdateSaleOrder', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (60, '销售单删除', 'flow:saleOrder:delete', '57', 'button', '/saleOrder/deleteSaleOrder', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (61, '销售单查询', 'flow:saleOrder:select', '57', 'button', '/saleOrder/selectSaleOrderByPage', null);
-- 原料出库单
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (62, '原料出库单', 'flow:rawOutOrder:*', '51', 'menu', '/rawOutOrder/*', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (63, '原料出库单添加', 'flow:rawOutOrder:save', '62', 'button', '/rawOutOrder/*' , null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (64, '原料出库单修改', 'flow:rawOutOrder:update', '62', 'button', '/rawOutOrder/checkRawOutOrderById', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (65, '原料出库单删除', 'flow:rawOutOrder:delete', '62', 'button', '/rawOutOrder/*', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (66, '原料出库单查询', 'flow:rawOutOrder:select', '62', 'button', '/rawOutOrder/selectRawOutOrderByPage', null);
-- 商品入库单
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (67, '商品入库单', 'flow:goodsInOrder:*', '51', 'menu', '/goodsInOrder/*', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (68, '商品入库单添加', 'flow:goodsInOrder:save', '67', 'button', '/goodsInOrder/*' , null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (69, '商品入库单修改', 'flow:goodsInOrder:update', '67', 'button', '/goodsInOrder/checkGoodsInOrderById', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (70, '商品入库单删除', 'flow:goodsInOrder:delete', '67', 'button', '/goodsInOrder/*', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (71, '商品入库单查询', 'flow:goodsInOrder:select', '67', 'button', '/goodsInOrder/selectgGodsInOrderByPage', null);
-- 商品出库单
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (72, '商品出库单', 'flow:goodsOutOrder:*', '51', 'menu', '/goodsOutOrder/*', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (73, '商品出库单添加', 'flow:goodsOutOrder:save', '72', 'button', '/goodsOutOrder/saveOrUpdateGoodsOutOrder' , null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (74, '商品出库单修改', 'flow:goodsOutOrder:update', '72', 'button', '/goodsOutOrder/saveOrUpdateGoodsOutOrder', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (75, '商品出库单删除', 'flow:goodsOutOrder:delete', '72', 'button', '/goodsOutOrder/deleteGoodsOutOrder', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (76, '商品出库单查询', 'flow:goodsOutOrder:select', '72', 'button', '/goodsOutOrder/selectGoodsOutOrderByPage', null);
-- ================================================================================================================================================================================================
-- 生产加工
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (77, '生产加工', 'process:*', '0', 'menu', null, null);
-- 物料加工
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (78, '物料加工', 'process:material:*', '77', 'menu', '/processMaterial/*', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (79, '物料加工添加', 'process:material:save', '78', 'button', '/processMaterial/saveProcessMaterial' , '加工一个原料');
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (80, '物料加工批量添加', 'process:material:batchSave', '78', 'button', '/processMaterial/saveProcessMaterial' , '批量加工原料');
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (81, '物料加工修改', 'process:material:update', '78', 'button', '/processMaterial/*', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (82, '物料加工删除', 'process:material:delete', '78', 'button', '/processMaterial/deleteProcessMaterial', null);
INSERT INTO sys_permission (id, permission_name, permission_code, parent_id, type, url, description) VALUES (83, '物料加工查询', 'process:material:select', '78', 'button', '/processMaterial/selectProcessMaterialByPage', null);
