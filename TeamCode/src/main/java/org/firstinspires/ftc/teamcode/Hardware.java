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
    public double LiftUpVelocity = 300;
    public double LiftDownVelocity = 300;
    public double triggerFire = 1;
    public double actuatorVelocity = 300;
    public double triggerLock = 0;
    public double Speed1 = 0.5;
    public double Speed2 = 1;
    public double Speed3 = 0.25;

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

        //RightLift = hwMap.get(DcMotorEx.class, "----");
        //LeftLift = hwMap.get(DcMotorEx.class, "----");

        Actuator = hwMap.get(DcMotorEx.class, "act");

        LeftDriveRear.setDirection(DcMotorEx.Direction.FORWARD);
        LeftDriveFront.setDirection(DcMotorEx.Direction.FORWARD);
        RightDriveFront.setDirection(DcMotorEx.Direction.FORWARD);
        RightDriveRear.setDirection(DcMotorEx.Direction.FORWARD);

        //RightLift.setDirection(DcMotorEx.Direction.FORWARD);
        //LeftLift.setDirection(DcMotorEx.Direction.FORWARD);

        Actuator.setDirection(DcMotorEx.Direction.FORWARD);

        LeftDriveRear.setPower(0);
        LeftDriveFront.setPower(0);
        RightDriveFront.setPower(0);
        RightDriveRear.setPower(0);

        //RightLift.setPower(0);
        //LeftLift.setPower(0);

        Actuator.setPower(0);


        LeftDriveRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LeftDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightDriveFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightDriveRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //RightLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //LeftLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Actuator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        LeftDriveRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LeftDriveFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightDriveFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightDriveRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //RightLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //LeftLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Actuator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        trigger = hwMap.get(Servo.class, "----");

        trigger.setPosition(triggerLock);
    }

}

