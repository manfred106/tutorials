package com.baeldung.algorithms.optimization.lp;

import org.ojalgo.optimisation.ExpressionsBasedModel;
import org.ojalgo.optimisation.Expression;
import org.ojalgo.optimisation.Variable;

public class AssignmentProblem {

    public static void main(String[] args) {

        double[][] cost = {
            {27, 6, 21},
            {18, 12, 9},
            {15, 24, 3}
        };

        int volunteers = cost.length;
        int locations = cost[0].length;

        ExpressionsBasedModel model = new ExpressionsBasedModel();

        Variable[][] assignment = new Variable[3][3];

        // Create binary decision variables
        for (int i = 0; i < volunteers; i++) {
            for (int j = 0; j < locations; j++) {
                assignment[i][j] = model
                    .newVariable("assignment_" + i + "_" + j)
                    .binary()
                    .weight(cost[i][j]);
            }
        }

        // Each volunteer is assigned to exactly one location
        for (int i = 0; i < volunteers; i++) {
            Expression volunteerConstraint =
                model.addExpression("Volunteer_" + i).level(1);

            for (int j = 0; j < locations; j++) {
                volunteerConstraint.set(assignment[i][j], 1);
            }
        }

        // Each location gets exactly one volunteer
        for (int j = 0; j < locations; j++) {
            Expression locationConstraint =
                model.addExpression("Location_" + j).level(1);

            for (int i = 0; i < volunteers; i++) {
                locationConstraint.set(assignment[i][j], 1);
            }
        }

        // Solve
        var result = model.minimise();

        System.out.println("Minimum total travel time: " + result.getValue());

        model.getVariables().forEach(v -> {
            //System.out.println(v.getName() + " = " + v.getValue());
            if (v.getValue().doubleValue() == 1.0) {
                System.out.println(v.getName() + " = 1");
            }
        });
    }
}