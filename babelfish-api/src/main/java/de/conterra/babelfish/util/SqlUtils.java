package de.conterra.babelfish.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * defines a utility class to extend the JoSQL lib
 * 
 * @version 0.1
 * @author chwe
 * @since 0.1
 */
public class SqlUtils
{
	/**
	 * the {@link Logger} of this class
	 * 
	 * @since 0.1
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(SqlUtils.class);
	
	/**
	 * all SQL operators, where a column name could stand in front to
	 * 
	 * @since 0.1
	 */
	private static final String[] OPERATORS = new String[]
	{
		"=",
		"<>",
		"!=",
		">",
		"<",
		">=",
		"<=",
		"BETWEEN",
		"LIKE",
		"IN",
	};
	
	/**
	 * private standard constructor, to prevent initialization
	 * 
	 * @since 0.1
	 */
	private SqlUtils()
	{
	}
	
	/**
	 * mask a column in a SQL {@link String} to make it unique
	 * 
	 * @since 0.1
	 * 
	 * @param exp the expression to mask
	 * @return the masked expression
	 */
	private static String maskSql(String exp)
	{
		String mask = "@";
		
		return (mask + exp + mask).replaceAll(" ", "#");
	}
	
	/**
	 * gives a {@link Set} of all SQL operators, where a column name could stand
	 * on the left side
	 * 
	 * @since 0.1
	 * 
	 * @return gives a {@link Set} of all SQL operators
	 */
	private static String[] getOperators()
	{
		List<String> list = new ArrayList<>();
		
		for (String operator : SqlUtils.OPERATORS)
		{
			if ( ! (list.contains(operator)))
			{
				list.add(operator);
				
				String notOperator = "NOT " + operator;
				if ( ! (list.contains(notOperator)))
					list.add(notOperator);
			}
		}
		
		Collections.sort(list, new Comparator<String>()
		{
			@Override
			public int compare(String first, String second)
			{
				return second.length() - first.length();
			}
		});
		
		String[] result = new String[list.size()];
		
		for (int i = 0; i < result.length; i++)
			result[i] = list.get(i);
		
		return result;
	}
	
	/**
	 * replaces all column names in a SQL {@link String} to a special method<br>
	 * This was created, because the JoSQL library only supports direct getters,
	 * so it tries to use a getColumnName() method.
	 * 
	 * @since 0.1
	 * 
	 * @param sql the SQL {@link String} to replace the column names in
	 * @param method to enclose the column name with
	 * @return the prepared SQL {@link String}
	 */
	public static String replaceColumn(String sql, String method)
	{
		String result = sql;
		SqlUtils.LOGGER.debug("Mask all operators of statement: " + sql);
		String[] operators = SqlUtils.getOperators();
		for (String operator : operators)
			result = result.replaceAll(operator, SqlUtils.maskSql(operator));
		result = result.replaceAll("#@|@#", "#");
		result = result.replaceAll("@@", "@");
		SqlUtils.LOGGER.debug("All operators masked. Masked statement: " + result);
		
		for (String operator : operators)
		{
			SqlUtils.LOGGER.debug("Execute column id replacement at operator " + operator + ". Current statement: " + result);
			
			String maskedOperator = SqlUtils.maskSql(operator);
			String newSql = "";
			
			String[] parts = result.split(maskedOperator);
			for (int i = 0; i < parts.length - 1; i++)
			{
				String cleanPart = parts[i];
				
				while (cleanPart.endsWith(" "))
					cleanPart = cleanPart.substring(0, cleanPart.length() - 1);
				
				int spaceIndex = 0;
				Matcher matcher = Pattern.compile("[^a-zA-Z0-9_]").matcher(cleanPart);
				while (matcher.find())
					spaceIndex = matcher.end();
				
				SqlUtils.LOGGER.debug("Found cloumn id at " + spaceIndex + " in " + cleanPart);
				
				newSql += cleanPart.substring(0, spaceIndex) + method + "('" + cleanPart.substring(spaceIndex) + "') " + maskedOperator + " ";
			}
			
			result = newSql + parts[parts.length - 1];
			
			SqlUtils.LOGGER.debug("All column ids of operator " + operator + " replaced. New statement: " + result);
		}
		
		SqlUtils.LOGGER.debug("Unmask all operators of statement: " + result);
		for (String operator : operators)
			result = result.replaceAll(SqlUtils.maskSql(operator), operator);
		SqlUtils.LOGGER.debug("All operators unmasked. Result statement: " + result);
		
		return result;
	}
}