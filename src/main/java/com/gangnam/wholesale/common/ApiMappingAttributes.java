package com.gangnam.wholesale.common;

public class ApiMappingAttributes {

	private ApiMappingAttributes() {
		throw new IllegalStateException("Utility class");
	}

	public static final String ADMIN_PRODUCT_API = "/admin/api/products";
	public static final String PRODUCT_API = "/api/products";

	public static final String ADMIN_CATEGORY_API = "/admin/api/categories";
	public static final String CATEGORY_API = "/api/categories";
	public static final String SUB_CATEGORY_API = "/{id}/sub-categories";

	public static final String ADMIN_ORDER_API = "/admin/api/orders";
	public static final String ORDER_API = "/api/orders";

	public static final String ID = "/{id}";

}
