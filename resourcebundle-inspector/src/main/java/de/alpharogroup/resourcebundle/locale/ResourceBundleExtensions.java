package de.alpharogroup.resourcebundle.locale;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import de.alpharogroup.check.Check;
import lombok.experimental.UtilityClass;

/**
 * The class {@link ResourceBundleExtensions} holds extension methods for the
 * class {@link ResourceBundle}.
 */
@UtilityClass
public class ResourceBundleExtensions {

	/** The Constant logger. */
	protected static final Logger LOGGER = Logger.getLogger(ResourceBundleExtensions.class.getName());

	/**
	 * Gets the string from the given {@link ResourceBundle} object.
	 *
	 * @param resourceBundle
	 *            the resource bundle to get the {@link String} object.
	 * @param key
	 *            the properties key
	 * @param parameters
	 *            the parameters
	 * @return the {@link String} value from the given key
	 * @throws MissingResourceException is thrown if the given key is missing
	 */
	public static String getString(final ResourceBundle resourceBundle, final String key, final Object... parameters) throws MissingResourceException {
		return getString(resourceBundle, key, null, parameters);
	}

	/**
	 * Gets the string from the given {@link ResourceBundle} object.
	 *
	 * @param resourceBundle
	 *            the resource bundle to get the {@link String} object.
	 * @param key
	 *            the properties key
	 * @param parameters
	 *            the parameters
	 * @return the {@link String} value from the given key
	 */
	public static String getStringQuietly(final ResourceBundle resourceBundle, final String key, final Object... parameters) {
		return getStringQuietly(resourceBundle, key, null, parameters);
	}

	/**
	 * Gets the string from the given {@link ResourceBundle} object.
	 *
	 * @param resourceBundle
	 *            the resource bundle to get the {@link String} object.
	 * @param key
	 *            the properties key
	 * @param defaultValue
	 *            the default value
	 * @return the {@link String} value from the given key
	 * @throws MissingResourceException is thrown if the given key is missing
	 */
	public static String getString(final ResourceBundle resourceBundle, final String key, final String defaultValue) throws MissingResourceException {
		return getString(resourceBundle, key, defaultValue, (Object) null);
	}

	/**
	 * Gets the string from the given {@link ResourceBundle} object.
	 *
	 * @param resourceBundle
	 *            the resource bundle to get the {@link String} object.
	 * @param key
	 *            the properties key
	 * @param defaultValue
	 *            the default value
	 * @return the {@link String} value from the given key
	 */
	public static String getStringQuietly(final ResourceBundle resourceBundle, final String key, final String defaultValue) {
		return getStringQuietly(resourceBundle, key, defaultValue, (Object) null);
	}

	/**
	 * Gets the string from the given {@link ResourceBundle} object.
	 *
	 * @param resourceBundle
	 *            the resource bundle to get the {@link String} object.
	 * @param key
	 *            the properties key
	 * @param defaultValue
	 *            the default value
	 * @param parameters
	 *            the parameters
	 * @return the {@link String} value from the given key
	 * @throws MissingResourceException is thrown if the given key is missing
	 */
	public static String getString(final ResourceBundle resourceBundle, final String key, final String defaultValue,
			final Object... parameters) throws MissingResourceException {
		Check.get().notNull(resourceBundle, "resourceBundle").notEmpty(key, "key");
		String value = null;
		value = resourceBundle.getString(key);
		if (parameters != null && 0 < parameters.length) {
			value = MessageFormat.format(value, parameters);
		}
		return value;
	}

	/**
	 * Gets the string from the given {@link ResourceBundle} object.
	 *
	 * @param resourceBundle
	 *            the resource bundle to get the {@link String} object.
	 * @param key
	 *            the properties key
	 * @param defaultValue
	 *            the default value
	 * @param parameters
	 *            the parameters
	 * @return the {@link String} value from the given key
	 */
	public static String getStringQuietly(final ResourceBundle resourceBundle, final String key, final String defaultValue,
			final Object... parameters) {
		Check.get().notNull(resourceBundle, "resourceBundle").notEmpty(key, "key");
		String value = null;
		try {
			value = getString(resourceBundle, key, defaultValue, parameters);
		} catch (final MissingResourceException e) {
			final String warnMessage = "Warning:!!!Missing key is '" + key + "'!!!";
			if (defaultValue != null && !defaultValue.isEmpty()) {
				LOGGER.warn(warnMessage, e);
				return defaultValue;
			}
			return warnMessage;
		}
		if (parameters != null && 0 < parameters.length) {
			value = MessageFormat.format(value, parameters);
		}
		return value;
	}

	/**
	 * Resolves from the given base name and locale the {@link ResourceBundle}
	 * and get the {@link String} from the given key and parameters.
	 *
	 * @param baseName
	 *            the base name of the resource bundle, a fully qualified class
	 *            name
	 * @param locale
	 *            the locale for the resource bundle
	 * @param key
	 *            the properties key
	 * @param defaultValue
	 *            the default value
	 * @param parameters
	 *            the parameters
	 * @return the {@link String} value from the given key
	 * @throws MissingResourceException is thrown if the given key is missing
	 */
	public static String getString(final String baseName, final Locale locale, final String key,
			final String defaultValue, final Object... parameters) throws MissingResourceException {
		Check.get().notEmpty(baseName, "baseName").notNull(locale, "locale");
		final ResourceBundle resourceBundle = ResourceBundleResolver.getBundle(baseName, locale);
		return ResourceBundleExtensions.getString(resourceBundle, key, defaultValue, parameters);
	}

	/**
	 * Resolves from the given base name and locale the {@link ResourceBundle}
	 * and get the {@link String} from the given key and parameters.
	 *
	 * @param baseName
	 *            the base name of the resource bundle, a fully qualified class
	 *            name
	 * @param locale
	 *            the locale for the resource bundle
	 * @param key
	 *            the properties key
	 * @param defaultValue
	 *            the default value
	 * @param parameters
	 *            the parameters
	 * @return the {@link String} value from the given key
	 */
	public static String getStringQuietly(final String baseName, final Locale locale, final String key,
			final String defaultValue, final Object... parameters) {
		Check.get().notEmpty(baseName, "baseName").notNull(locale, "locale");
		final ResourceBundle resourceBundle = ResourceBundleResolver.getBundle(baseName, locale);
		return ResourceBundleExtensions.getStringQuietly(resourceBundle, key, defaultValue, parameters);
	}

	/**
	 * Resolves from the given base name and locale the {@link ResourceBundle}
	 * and get the {@link String} from the given key and parameters.
	 *
	 * @param baseName
	 *            the base name of the resource bundle, a fully qualified class
	 *            name
	 * @param locale
	 *            the locale for the resource bundle
	 * @param key
	 *            the properties key
	 * @param parameters
	 *            the parameters
	 * @return the {@link String} value from the given key
	 * @throws MissingResourceException is thrown if the given key is missing
	 */
	public static String getString(final String baseName, final Locale locale, final String key,
			final Object... parameters) throws MissingResourceException {
		return getString(baseName, locale, key, null, parameters);
	}

	/**
	 * Resolves from the given base name and locale the {@link ResourceBundle}
	 * and get the {@link String} from the given key and parameters.
	 *
	 * @param baseName            the base name of the resource bundle, a fully qualified class
	 *            name
	 * @param locale            the locale for the resource bundle
	 * @param key            the properties key
	 * @return the {@link String} value from the given key
	 * @throws MissingResourceException is thrown if the given key is missing
	 */
	public static String getString(final String baseName, final Locale locale, final String key) throws MissingResourceException {
		return getString(baseName, locale, key, (String)null, (Object)null);
	}

	/**
	 * Resolves from the given base name and locale the {@link ResourceBundle}
	 * and get the {@link String} from the given key and parameters.
	 *
	 * @param baseName
	 *            the base name of the resource bundle, a fully qualified class
	 *            name
	 * @param locale
	 *            the locale for the resource bundle
	 * @param key
	 *            the properties key
	 * @param defaultValue
	 *            the default value
	 * @return the {@link String} value from the given key
	 * @throws MissingResourceException is thrown if the given key is missing
	 */
	public static String getString(final String baseName, final Locale locale, final String key,
			final String defaultValue) throws MissingResourceException {
		return getString(baseName, locale, key, defaultValue, (Object)null);
	}

	/**
	 * Resolves the {@link String} object from the given {@link BundleKey}.
	 *
	 * @param bundleKey
	 *            the bundle key
	 * @return the {@link String} value from the given {@link BundleKey}.
	 * @throws MissingResourceException is thrown if the given key is missing
	 */
	public static String getString(final BundleKey bundleKey) throws MissingResourceException {
		return getString(bundleKey.getBaseName(), bundleKey.getLocale(), bundleKey.getResourceBundleKey().getKey(),
				bundleKey.getResourceBundleKey().getDefaultValue(), bundleKey.getResourceBundleKey().getParameters());
	}

}
