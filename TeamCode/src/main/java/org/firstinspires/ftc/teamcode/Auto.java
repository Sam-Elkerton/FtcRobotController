package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.YZX;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.FRONT;
@Autonomous(name="Auto Java", group="Linear Opmode")

public class Auto extends LinearOpMode{
    private Hardware robot   = new Hardware();
    private  Drive DriveTrain = new Drive(robot);
    private Suspend Actuator = new Suspend(robot);
    private Lift PixelPlace = new Lift(robot);
    private Launch LaunchPlane = new Launch(robot);
    HardwareMap hwMap = null;
    private BNO055IMU imu;
    private BNO055IMU.Parameters imuParameters;
    private Orientation angles;
    private Acceleration gravity;
    @Override
    public void runOpMode(){
        telemetry.addData("Status", "Initializing");
        telemetry.update();
        InitializeRobot();
        GetPosition();

        telemetry.addData("Encoder Drive Test", "Running...");
        telemetry.update();
        DriveTrain.EncoderDrive(1, 10, 10, 10, GetPosition());
        DriveTrain.EncoderDrive(1, 10, 10, 10, GetPosition()+90);
        DriveTrain.EncoderDrive(1, 10, 5, 10, GetPosition());
        DriveTrain.EncoderDrive(1, 5, 10, 10, GetPosition());

        telemetry.addData("Power Drive Test", "Running...");
        telemetry.update();
        DriveTrain.PowerDrive(0.5, 0.5, 0.5, 0.5);
        DriveTrain.PowerDrive(0.5, -0.5, -0.5, 0.5);
        DriveTrain.PowerDrive(-0.5, 0.5, 0.5, -0.5);
        DriveTrain.PowerDrive(-0.5, -0.5, -0.5, -0.5);

        telemetry.addData("Acuator Test", "Running...");
        telemetry.update();
        Actuator.positionActuator(4);
        Actuator.positionActuator(0);

        telemetry.addData("PixelPlace Test", "Running...");
        telemetry.update();
        PixelPlace.positionLift(4);
        PixelPlace.positionLift(0);

        telemetry.addData("Plane Launch Test", "Running...");
        telemetry.update();
        LaunchPlane.release();



;    }

    public void InitializeRobot() {

        robot.init(hwMap);

        imu = hwMap.get(BNO055IMU.class, "imu");
        imuParameters = new BNO055IMU.Parameters();
        imuParameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        imuParameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        imuParameters.loggingEnabled = false;

        imu.initialize(imuParameters);

    }

    public double GetPosition() {

        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        gravity = imu.getGravity();
        telemetry.addData("rot about Z", angles.firstAngle);
        telemetry.addData("rot about Y", angles.secondAngle);
        telemetry.addData("rot about X", angles.thirdAngle);
        telemetry.addData("gravity (Z)", gravity.zAccel);
        telemetry.addData("gravity (Y)", gravity.yAccel);
        telemetry.addData("gravity (X)", gravity.xAccel);
        telemetry.update();
        return  angles.firstAngle;

    }
}
