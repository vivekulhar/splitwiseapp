package dev.vivek.springapp.strategies;

import dev.vivek.springapp.models.User;
import org.antlr.v4.runtime.misc.Pair;

import java.util.Comparator;

// Custom comparator for Pair objects
class PairComparator implements Comparator<Pair<User, Integer>> {
    @Override
    public int compare(Pair<User, Integer> pair1, Pair<User, Integer> pair2) {
        // Compare based on the integer value in the Pair
        return pair1.b.compareTo(pair2.b);
    }
}