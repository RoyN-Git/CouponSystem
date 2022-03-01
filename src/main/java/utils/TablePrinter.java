package utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TablePrinter {
    // can add here field names you dont want to show,
    // for example - List.of("firstname") wont show the field named 'firstName' (insert all lowercase)
    public static List<String> noShowFields = new ArrayList<>(List.of());


    public static void print(Object item) {
        if (item != null) {
            if (item.getClass().isPrimitive()) {
                System.out.println(item);
            } else if (item instanceof Collection<?>) {
                print(List.copyOf((Collection<?>) item));
            } else {
                print(List.of(item));
            }
        } else {
            System.out.println("null");
        }
    }

    public static void print(List<?> list) {
        if (list.isEmpty()) {
            System.out.println("empty collection");
            return;
        }
        int index = 0;
        while (index < list.size() && list.get(index) == null) {
            index++;
        }
        if (index == list.size()) {
            System.out.println("collection of nulls");
        }

        Class<?> itemClass = list.get(index).getClass();

        List<List<String>> columns = new ArrayList<List<String>>();
        List<String> columnHeaders = new ArrayList<String>();
        List<Integer> columnWidths = new ArrayList<Integer>();

        Method[] methods = itemClass.getDeclaredMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith("get") == false) {
                continue;
            }
            methodName = methodName.substring(3);
            if (methodName.contains("$") || methodName.contains("Hibernate")) { // skips spring attributes
                continue;
            }
            if (noShowFields.contains(methodName.toLowerCase())) {
                continue;
            }
            List<String> columnStrings = getMethodStringValues(method, list);
            if (columnStrings.isEmpty()) {
                continue;
            }
            int columnWidth = Math.max(maxWidth(columnStrings), methodName.length());

            columnHeaders.add(methodName);
            columns.add(columnStrings);
            columnWidths.add(columnWidth);

        }

        int allWidth = 0;
        System.out.print("  ");
        for (int i = 0; i < columnHeaders.size(); i++) {
            String header = columnHeaders.get(i);
            int columnWidth = columnWidths.get(i);
            allWidth += columnWidth;
            System.out.print(padSpace(header, columnWidth));
            if (i < columnHeaders.size() - 1) {
                System.out.print(" | ");
                allWidth += 3;
            }
        }
        System.out.println();

        System.out.print("  ");
        System.out.println("-".repeat(allWidth));

        for (int rowIndex = 0; rowIndex < list.size(); rowIndex++) {
            System.out.print("  ");
            for (int columnIndex = 0; columnIndex < columns.size(); columnIndex++) {
                String value = columns.get(columnIndex).get(rowIndex);
                int columnWidth = columnWidths.get(columnIndex);
                System.out.print(padSpace(value, columnWidth));
                if (columnIndex < columns.size() - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
        }
        System.out.println("  " + "-".repeat(allWidth));
        System.out.println();

    }

    private static List<String> getMethodStringValues(Method method, List<?> objects) {
        List<String> values = new ArrayList<String>();
        if (Modifier.isStatic(method.getModifiers())) {
            return values;
        }
        method.setAccessible(true);
        for (Object object : objects) {
            if (object == null) {
                values.add("null");
                continue;
            }
            try {
                Object objectField = method.invoke(object, (Object[]) null);
                if (objectField == null) {
                    values.add("null");
                } else {
                    values.add(objectField.toString());
                }
            } catch (InvocationTargetException | IllegalAccessException | IllegalArgumentException e) {
            }
        }

        return values;
    }

    private static int maxWidth(List<String> ls) {
        int width = 0;
        for (String string : ls) {
            width = Math.max(string.length(), width);
        }
        return width;
    }

    private static String padSpace(String str, int length) {
        StringBuilder builder = new StringBuilder(str);
        while (builder.length() <= length - 2) {
            builder.append(' ');
            builder.insert(0, ' ');
        }
        if (builder.length() < length) {
            builder.insert(0, ' ');
        }
        return builder.toString();
    }
}