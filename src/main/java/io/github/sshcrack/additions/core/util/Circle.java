package io.github.sshcrack.additions.core.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector2f;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Circle {
    public static ArrayList<Vector2f> getFullCircle(float radius, Vector2f offset) {
        ArrayList<Vector2f> list = new ArrayList<>();
        ArrayList<Vector2f> circle = getCircle(radius, offset);

        for(int i = 0; i < circle.size(); i++) {
            Vector2f firstItem = circle.get(i);
            float y = firstItem.y;
            List<Vector2f> row = circle
                    .stream()
                    .filter(e -> e.y == y)
                    .sorted((first, second) ->
                            (int) (first.x - second.x))
                    .collect(Collectors.toList());

            if(row.size() < 2) {
                list.addAll(row);
                continue;
            }

            Vector2f first = row.get(0);
            Vector2f second = row.get(row.size() -1);
            float currX = first.x;

            while(currX < second.x) {
                currX++;
                Vector2f currItem = new Vector2f(currX, y);
                list.add(currItem);
            }
        }

        return list;
    }

    public static ArrayList<Vector2f> getCircle(float radius, Vector2f offset) {
        ArrayList<Vector2f> list = new ArrayList<>();

        float d = Math.round(Math.PI - (2 * radius));
        float x = 0;
        float y = radius;


        while(x <= y) {
            list.add(toAbsolute(x, -y, offset));
            list.add(toAbsolute(y, -x, offset));
            list.add(toAbsolute(y, x, offset));
            list.add(toAbsolute(x, y, offset));
            list.add(toAbsolute(-x, y, offset));
            list.add(toAbsolute(-y, x, offset));
            list.add(toAbsolute(-y, -x, offset));
            list.add(toAbsolute(-x, -y, offset));

            if (d < 0) {
                d = (float) (d + (Math.PI * x) + (Math.PI * 2));
            } else {
                d = (float) (d + Math.PI * (x - y) + (Math.PI * 3));
                y--;
            }

            x++;
        }

        return list;
    }

    public static Vector2f toAbsolute(float localX, float localY, Vector2f offset) {
        float x = localX + offset.x;
        float y = localY + offset.y;

        return new Vector2f(x, y);
    }

    public static Vector2f blockToVector2D(BlockPos pos) {
        float x = (float) (pos.getX() + .5);
        float y = (float) (pos.getZ() + .5);

        return new Vector2f(x, y);
    }
}
