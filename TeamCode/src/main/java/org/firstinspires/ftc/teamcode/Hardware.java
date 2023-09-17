package org.firstinspires.ftc.teamcode;

import android.widget.Button;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

public class Hardware {
    //drive motor declarations
    public DcMotorEx LeftDriveRear = null;
    public DcMotorEx RightDriveRear = null;
    public DcMotorEx LeftDriveFront = null;
    public DcMotorEx RightDriveFront = null;

    //lift motor declarations
    public DcMotorEx RightLift = null;
    public DcMotorEx LeftLift = null;

    //claw servo declarations
    public Servo RightClaw = null;
    public Servo LeftClaw = null;

    //ticks per second
    public double LiftUpVelocity = 300;
    public double LiftDownVelocity = 300;

    //servo positions
    public double RightClawOpen = 1;
    public double LeftClawOpen = 1;
    public double RightClawClosed = 0;
    public double LeftClawClosed = 0;

    //movement speed
    public double Speed1 = 0.5;
    public double Speed2 = 1;
    public double Speed3 = 0.25;

    IMU imu;

    HardwareMap hwMap = null;

    private ElapsedTime period = new ElapsedTime();

    public Hardware(){

    }

    public void CalculateCOUNTS_PER_MOTOR_REV(double cpr, double gearRedox, double wheelDiameter){
        //encoders values for COUNTS_PER_INCH of lift
        double COUNTS_PER_MOTOR_REV = cpr;     //eg: Gobilda 13.7:1 Motor
        double DRIVE_GEAR_REDUCTION = gearRedox;     //No gear reduction on lift
        double WHEEL_DIAMETER_INCHES = wheelDiameter;    //Need to check this
        double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
    }


    public void init(HardwareMap ahwMap){

        hwMap = ahwMap;

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot( RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));

        imu = hwMap.get(IMU.class, "imu");

        //make sure to put the correct config names in place of the "----"
        //these can be found in the configuration on the Driver Station
        LeftDriveRear = hwMap.get(DcMotorEx.class, "----");
        LeftDriveFront = hwMap.get(DcMotorEx.class, "----");
        RightDriveFront = hwMap.get(DcMotorEx.class, "----");
        RightDriveRear = hwMap.get(DcMotorEx.class, "----");

        RightLift = hwMap.get(DcMotorEx.class, "----");
        LeftLift = hwMap.get(DcMotorEx.class, "----");

        LeftDriveRear.setDirection(DcMotorEx.Direction.FORWARD);
        LeftDriveFront.setDirection(DcMotorEx.Direction.FORWARD);
        RightDriveFront.setDirection(DcMotorEx.Direction.FORWARD);
        RightDriveRear.setDirection(DcMotorEx.Direction.FORWARD);

        RightLift.setDirection(DcMotorEx.Direction.FORWARD);
        LeftLift.setDirection(DcMotorEx.Direction.FORWARD);

        LeftDriveRear.setPower(0);
        LeftDriveFront.setPower(0);
        RightDriveFront.setPower(0);
        RightDriveRear.setPower(0);

        RightLift.setPower(0);
        LeftLift.setPower(0);


        LeftDriveRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LeftDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightDriveRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        RightLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LeftLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        LeftDriveRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LeftDriveFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightDriveFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightDriveRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        RightLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LeftLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        RightClaw = hwMap.get(Servo.class, "----");
        LeftClaw = hwMap.get(Servo.class, "----");

        RightClaw.setPosition(RightClawClosed);
        LeftClaw.setPosition(LeftClawClosed);
    }
}
