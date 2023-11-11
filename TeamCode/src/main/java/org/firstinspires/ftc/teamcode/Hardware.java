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
    public DcMotorEx LeftDriveRear = null;
    public DcMotorEx RightDriveRear = null;
    public DcMotorEx LeftDriveFront = null;
    public DcMotorEx RightDriveFront = null;
    public DcMotorEx RightLift = null;
    public DcMotorEx LeftLift = null;
    public DcMotorEx Actuator = null;
    public Servo trigger = null;

    public Servo claw1 = null;
    public Servo claw2 = null;
    public Servo drop = null;
    public Servo flip1 = null;
    public Servo flip2 = null;
    public Servo flip3 = null;
    public Servo flip4 = null;
    public double triggerFire = 1;
    public double triggerLock = 0;

    public  double     COUNTS_PER_MOTOR_REV    = 1120 ;
    public  double     DRIVE_GEAR_REDUCTION    = 1.0 ;
    public  double     WHEEL_DIAMETER_INCHES   = 4.0 ;
    public  double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

    IMU imu;

    HardwareMap hwMap = null;

    private ElapsedTime period = new ElapsedTime();

    public Hardware(){

    }

    public Hardware(double cpr, double gearRedox, double wheelDiameter){
        double COUNTS_PER_MOTOR_REV = cpr;
        double DRIVE_GEAR_REDUCTION = gearRedox;
        double WHEEL_DIAMETER_INCHES = wheelDiameter;
        double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
    }


    public void init(HardwareMap ahwMap){

        hwMap = ahwMap;

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot( RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));

        imu = hwMap.get(IMU.class, "imu");

        LeftDriveRear = hwMap.get(DcMotorEx.class, "bl");
        LeftDriveFront = hwMap.get(DcMotorEx.class, "fl");
        RightDriveFront = hwMap.get(DcMotorEx.class, "fr");
        RightDriveRear = hwMap.get(DcMotorEx.class, "br");

        RightLift = hwMap.get(DcMotorEx.class, "rl");
        LeftLift = hwMap.get(DcMotorEx.class, "ll");


        LeftDriveRear.setDirection(DcMotorEx.Direction.REVERSE);
        LeftDriveFront.setDirection(DcMotorEx.Direction.REVERSE);
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

        claw1 = hwMap.get(Servo.class, "c2");
        claw2 = hwMap.get(Servo.class, "c2");
        trigger = hwMap.get(Servo.class, "t1");
        drop = hwMap.get(Servo.class, "d1");
        flip1 = hwMap.get(Servo.class, "s1");
        flip2 = hwMap.get(Servo.class, "s2");
        flip3 = hwMap.get(Servo.class, "s3");
        flip4 = hwMap.get(Servo.class, "s4");

        trigger.setPosition(triggerLock);
        claw1.setPosition(0);
        claw2.setPosition(0);
        drop.setPosition(0);
        flip1.setPosition(0);
        flip2.setPosition(0);
        flip3.setPosition(0);
        flip4.setPosition(0);
    }

}

