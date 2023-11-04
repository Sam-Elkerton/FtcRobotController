package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "K", group = "TeleOp")
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

            telemetry.update();
            Drive DriveTrain = new Drive(robot) ;


            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x * 1.1;
            double rx = gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            DriveTrain.PowerDrive(frontLeftPower, frontRightPower, backLeftPower, backRightPower);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();

        }
    }

    public void InitializeMotors(){
        robot.init(hardwareMap);
}

}
