package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="Main Java", group="Linear Opmode")

public class Main extends LinearOpMode{

    private ElapsedTime runtime = new ElapsedTime();
    private Hardware robot   = new Hardware();
    private float clawPosition;
    private double leftPower;
    private double rightPower;

    private int liftFull = 0;
    private int actuatorFull = 0;


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        InitializeMotors();
        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {


            telemetry.addData("Calling", "DriveTrain");
            telemetry.addData("Calling", "LaunchPlane");
            telemetry.addData("Calling", "PixelPlace");
            telemetry.addData("Calling", "Actuator");
            telemetry.update();
            Drive DriveTrain = new Drive(robot) ;
            Launch LaunchPlane = new Launch(robot);
            Lift PixelPlace = new Lift(robot);
            Suspend Actuator = new Suspend(robot);


            double drive = -gamepad1.left_stick_y;
            double turn  =  gamepad1.right_stick_x;
            leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
            rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;


            DriveTrain.PowerDrive(leftPower, rightPower, leftPower, rightPower);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();

            if(gamepad1.a == true){
                LaunchPlane.release();
                telemetry.addData("Servo", "Releasing...");
                telemetry.update();
            }

            //if(gamepad1.b == true){
            //    PixelPlace.positionLift(liftFull);
            //    telemetry.addData("PixelPlace" , "Extending...");
            //    telemetry.update();
            //}

            if(gamepad1.x = true){
                Actuator.positionActuator(actuatorFull);
                telemetry.addData("Actuator", "Extending...");
                telemetry.update();
            }
        }
    }

    public void InitializeMotors(){
        robot.init(hardwareMap);
}

}
