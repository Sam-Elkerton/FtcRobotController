package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Suspend{
    private ElapsedTime runTime = new ElapsedTime();
    public int extendFull = 0;
    public int retractFull = 0;
    Hardware robot = null;

    public Suspend(Hardware aRobot) { robot = aRobot;}

    public void positionActuator(int pos){
        double aSpeed = 0.5;
        InitiateEncoders(pos);
        robot.Actuator.setPower(Math.abs(aSpeed));

        while(robot.Actuator.isBusy()){

        }
        robot.Actuator.setPower(0);
    }

    public void InitiateEncoders(double inches){
        robot.Actuator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        CalculateTarget(inches);
        robot.Actuator.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    public void CalculateTarget(double inches){
        int newTarget;

        newTarget = (int)(inches * robot.COUNTS_PER_INCH);
        robot.Actuator.setTargetPosition(newTarget);
    }


}
