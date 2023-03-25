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

    public void ButtonPressLimiter(double time){
        /*
        --The main purpose of this function is to get around the problem of having to use sleep() within TeleOp in order for the buttons to not be registered multiple times--
            - This is done by requiring a Boolean to be true in order for the button to be registered
            - This function uses ElapsedTime() to set that bool to be true only after a certain delay after previous press
            - This allows for an adjustable integer of how many times per second that the button can be pressed

       ** Key Things to Remember **
       Must reset the LastPress bool after any action is done following a button recognization
       Must also reset the ButtonPress bool to false after any action is done following a button recognization
         */
        boolean XButtonPress = true;
        boolean YButtonPress = true;
        boolean AButtonPress = true;
        boolean BButtonPress = true;
        boolean DpadUpButtonPress = true;
        boolean DpadDownButtonPress = true;
        boolean DpadLeftButtonPress = true;
        boolean DpadRightButtonPress = true;
        boolean LeftBumperButtonPress = true;
        boolean RightBumperButtonPress = true;
        boolean StartButtonPress = true;
        boolean BackButtonPress = true;

        ElapsedTime XLastPress = new ElapsedTime(0);
        ElapsedTime YLastPress = new ElapsedTime(0);
        ElapsedTime ALastPress = new ElapsedTime(0);
        ElapsedTime BLastPress = new ElapsedTime(0);
        ElapsedTime DpadUpLastPress = new ElapsedTime(0);
        ElapsedTime DpadDownLastPress = new ElapsedTime(0);
        ElapsedTime DpadLeftLastPress = new ElapsedTime(0);
        ElapsedTime DpadRightLastPress = new ElapsedTime(0);
        ElapsedTime LeftBumperLastPress = new ElapsedTime(0);
        ElapsedTime RightBumperLastPress = new ElapsedTime(0);
        ElapsedTime StartLastPress = new ElapsedTime(0);
        ElapsedTime BackLastPress = new ElapsedTime(0);

        if(time < XLastPress.milliseconds()){
            XButtonPress = true;
        }
        if(time < YLastPress.milliseconds()){
            YButtonPress = true;
        }
        if(time < ALastPress.milliseconds()){
            AButtonPress = true;
        }
        if(time < BLastPress.milliseconds()){
            BButtonPress = true;
        }
        if(time < DpadUpLastPress.milliseconds()){
            DpadUpButtonPress = true;
        }
        if(time < DpadDownLastPress.milliseconds()){
            DpadDownButtonPress = true;
        }
        if(time < DpadLeftLastPress.milliseconds()){
            DpadLeftButtonPress = true;
        }
        if(time < DpadRightLastPress.milliseconds()){
            DpadRightButtonPress = true;
        }
        if(time < LeftBumperLastPress.milliseconds()){
            LeftBumperButtonPress = true;
        }
        if(time < RightBumperLastPress.milliseconds()){
            RightBumperButtonPress = true;
        }
        if(time < StartLastPress.milliseconds()){
            StartButtonPress = true;
        }
        if(time < BackLastPress.milliseconds()){
            BackButtonPress = true;
        }


    }


    public void init(HardwareMap ahwMap){
        ButtonPressLimiter(0);

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
