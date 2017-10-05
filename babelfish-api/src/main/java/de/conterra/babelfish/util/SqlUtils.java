package de.conterra.babelfish.util;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * defines an utility class to extend the JoSQL lib
 *
 * @author ChrissW-R1
 * @version 0.4.0
 * @since 0.1.0
 */
@Slf4j
public class SqlUtils {
	/**
	 * all SQL operators, where a column name could stand in front to
	 *
	 * @since 0.1.0
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
	 * @since 0.1.0
	 */
	private SqlUtils() {
	}
	
	/**
	 * mask a column in a SQL {@link String} to make it unique
	 *
	 * @param exp the expression to mask
	 * @return the masked expression
	 *
	 * @since 0.1.0
	 */
	private static String maskSql(String exp) {
		String mask = "@";
		
		return (mask + exp + mask).replaceAll(" ", "#");
	}
	
	/**
	 * gives a {@link Set} of all SQL operators, where a column name could stand on the left side
	 *
	 * @return gives a {@link Set} of all SQL operators
	 *
	 * @since 0.1.0
	 */
	private static String[] getOperators() {
		List<String> list = new ArrayList<>();
		
		for (String operator : SqlUtils.OPERATORS) {
			if (!(list.contains(operator))) {
				list.add(operator);
				
				String notOperator = "NOT " + operator;
				if (!(list.contains(notOperator))) {
					list.add(notOperator);
				}
			}
		}
		
		Collections.sort(list, (first, second) -> second.length() - first.length());
		
		String[] result = new String[list.size()];
		
		for (int i = 0; i < result.length; i++) {
			result[i] = list.get(i);
		}
		
		return result;
	}
	
	/**
	 * replaces all column names in a SQL {@link String} to a special method<br>
	 * This was created, because the JoSQL library only supports direct getters, so it tries to use a getColumnName() method.
	 *
	 * @param sql    the SQL {@link String} to replace the column names in
	 * @param method to enclose the column name with
	 * @return the prepared SQL {@link String}
	 *
	 * @since 0.1.0
	 */
	public static String replaceColumn(String sql, String method) {
		String result = sql;
		log.debug("Mask all operators of statement: " + sql);
		String[] operators = SqlUtils.getOperators();
		for (String operator : operators) {
			result = result.replaceAll(operator, SqlUtils.maskSql(operator));
		}
		result = result.replaceAll("#@|@#", "#");
		result = result.replaceAll("@@", "@");
		log.debug("All operators masked. Masked statement: " + result);
		
		for (String operator : operators) {
			log.debug("Execute column id replacement at operator " + operator + ". Current statement: " + result);
			
			String        maskedOperator = SqlUtils.maskSql(operator);
			StringBuilder builder        = new StringBuilder();
			
			String[] parts = result.split(maskedOperator);
			for (int i = 0; i < parts.length - 1; i++) {
				String cleanPart = parts[i];
				
				while (cleanPart.endsWith(" ")) {
					cleanPart = cleanPart.substring(0, cleanPart.length() - 1);
				}
				
				int     spaceIndex = 0;
				Matcher matcher    = Pattern.compile("[^\\w]").matcher(cleanPart);
				while (matcher.find()) {
					spaceIndex = matcher.end();
				}
				
				log.debug("Found cloumn id at " + spaceIndex + " in " + cleanPart);
				
				builder.append(cleanPart.substring(0, spaceIndex) + method + "('" + cleanPart.substring(spaceIndex) + "') " + maskedOperator + " ");
			}
			
			result = builder.toString() + parts[parts.length - 1];
			
			log.debug("All column ids of operator " + operator + " replaced. New statement: " + result);
		}
		
		log.debug("Unmask all operators of statement: " + result);
		for (String operator : operators) {
			result = result.replaceAll(SqlUtils.maskSql(operator), operator);
		}
		log.debug("All operators unmasked. Result statement: " + result);
		
		return result;
	}
}
