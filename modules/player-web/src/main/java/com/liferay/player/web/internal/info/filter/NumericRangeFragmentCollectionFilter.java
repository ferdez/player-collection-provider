/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.player.web.internal.info.filter;

import com.liferay.fragment.collection.filter.FragmentCollectionFilter;
import com.liferay.fragment.renderer.FragmentRendererContext;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rubén Pulido
 */
@Component
public class NumericRangeFragmentCollectionFilter
	implements FragmentCollectionFilter {

	@Override
	public String getFilterKey() {
		return "numericRange";
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "numeric-range");
	}

	@Override
	public void render(
		FragmentRendererContext fragmentRendererContext,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		try {
			RequestDispatcher requestDispatcher =
				_servletContext.getRequestDispatcher("/page.jsp");

			requestDispatcher.include(httpServletRequest, httpServletResponse);
		}
		catch (Exception exception) {
			_log.error("Unable to render numeric range filter", exception);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		NumericRangeFragmentCollectionFilter.class);

	@Reference(target = "(osgi.web.symbolicname=com.liferay.player.web)")
	private ServletContext _servletContext;

}