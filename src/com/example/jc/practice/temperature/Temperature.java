package com.example.jc.practice.temperature;

/**
 * Утилитный класс для работы с температурами: расчёт среднесуточных значений,
 * определение периодов повышения температуры и т.д.
 */

public final class Temperature {

	private Temperature() {
	}

	/**
	 * Вычисляет средние температуры по дням недели.
	 *
	 * @param temperatureMorning   массив температур утром
	 * @param temperatureAfternoon массив температур днём
	 * @param temperatureEvening   массив температур вечером
	 * @param weekLength           длина недели (количество дней)
	 * @return массив средних температур за каждый день
	 */
	public static double[] averageTemperatures(int[] temperatureMorning, int[] temperatureAfternoon, int[] temperatureEvening,
			int weekLength) {
		double[] result = new double[temperatureMorning.length];

		for (int i = 0; i < weekLength; i++) {
			double temp = (temperatureMorning[i] + temperatureAfternoon[i] + temperatureEvening[i]);
			result[i] = temp / 3;
		}

		return result;
	}

	/**
	 * Находит все периоды повышения температуры (последовательные дни, в которые
	 * температура росла).
	 *
	 * @param averageTemperatures массив средних температур
	 * @return массив периодов, каждый из которых представлен парой
	 *         {начальный_индекс, длина}
	 */
	public static int[][] findAllPeriods(double[] averageTemperatures) {
		int[][] tempResult = new int[averageTemperatures.length][2];
		int startIndex = 0;
		int length = 0;
		int count = 0;

		for (int i = 1; i < averageTemperatures.length; i++) {
			if (averageTemperatures[i - 1] < averageTemperatures[i]) {
				if (length == 0) {
					startIndex = i - 1;
					length = 2;
				} else {
					length++;
				}
			} else {
				if (length > 1) {
					tempResult[count][0] = startIndex;
					tempResult[count][1] = length;
					count++;
				}
				length = 0;
			}
		}

		// Проверка, если рост температуры продолжается до конца массива
		if (length > 1) {
			tempResult[count][0] = startIndex;
			tempResult[count][1] = length;
			count++;
		}

		int[][] result = new int[count][2];
		for (int i = 0; i < count; i++) {
			result[i] = tempResult[i];
		}

		return result;
	}

	/**
	 * Находит самый длинный период повышения температуры.
	 *
	 * @param periods массив всех найденных периодов
	 * @return массив из двух элементов: {начальный_индекс, длина_периода}
	 */
	public static int[] findLongestPeriod(int[][] periods) {
		if (periods.length == 0)
			return new int[] { -1, 0 };

		int maxIndex = 0;
		for (int i = 1; i < periods.length; i++) {
			if (periods[i][1] > periods[maxIndex][1]) {
				maxIndex = i;
			}
		}
		return periods[maxIndex];
	}

	/**
	 * Выводит в консоль самый длинный период повышения температуры.
	 *
	 * @param dates  массив дат
	 * @param period массив с начальным индексом и длиной периода
	 */
	public static void printLongestPeriod(String[] dates, int[] period) {
		if (period[1] <= 1 || period[0] == -1) {
			System.out.println("\nПериодов повышения температуры не найдено.");
			return;
		}
		int start = period[0];
		int end = start + period[1] - 1;
		System.out.println("\nСамый длинный период повышения: " + (period[1] - 1) + " дня");
		System.out.println("(с " + dates[start] + " по " + dates[end] + ")");
	}

	/**
	 * Выводит в консоль все найденные периоды повышения температуры.
	 *
	 * @param dates   массив дат
	 * @param periods массив всех периодов повышения температуры
	 */
	public static void printAllPeriods(String[] dates, int[][] periods) {
		System.out.println("\nВсе периоды повышения температуры:");
		if (periods.length == 0) {
			System.out.println("Не найдено.");
			return;
		}

		for (int[] period : periods) {
			int start = period[0];
			int end = start + period[1] - 1;
			System.out.printf("с %s по %s (%d дня)%n", dates[start], dates[end], period[1] - 1);
		}
	}

	/**
	 * Выводит в консоль средние температуры по дням.
	 *
	 * @param dates массив дат
	 * @param averageTemperatures массив средних температур
	 */
	public static void printAverageTemperatures(String[] dates, double[] averageTemperatures) {
		System.out.println("Средние температуры:");
		for (int i = 0; i < dates.length; i++) {
			System.out.println(String.format("%s → %.1f", dates[i], averageTemperatures[i]));
		}
	}

}
