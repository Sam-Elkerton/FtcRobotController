package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Lift{
    private ElapsedTime runTime = new ElapsedTime();
    Hardware robot = null;

    public Lift(Hardware lRobot) { robot = lRobot;}

    public void positionLift(int pos){
        double lSpeed = 0.5;
        InitiateEncoders(pos);
        robot.RightLift.setPower(Math.abs(lSpeed));
        robot.LeftLift.setPower(Math.abs(lSpeed));

        while(robot.RightLift.isBusy() && robot.LeftLift.isBusy()){

        }
        robot.RightLift.setPower(0);
        robot.LeftLift.setPower(0);
    }

    public void InitiateEncoders(double inches){
        robot.RightLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.LeftLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        CalculateTarget(inches);
        robot.Actuator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.LeftLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    public void CalculateTarget(double inches){
        int newLeftTarget;
        int newRightTarget;

        newLeftTarget = (int)(inches * robot.COUNTS_PER_INCH);
        newRightTarget = (int)(inches * robot.COUNTS_PER_INCH);
        robot.LeftLift.setTargetPosition(newLeftTarget);
        robot.RightLift.setTargetPosition(newRightTarget);
    }


}
