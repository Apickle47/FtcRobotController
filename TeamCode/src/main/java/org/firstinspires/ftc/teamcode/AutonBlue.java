package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous(name = "Autonomous Test")
public class AutonBlue extends LinearOpMode {

    private OpenCvCamera webcam;
    private DcMotor armmotor2;
    private DcMotor frontLeft;
    private DcMotor backRight;
    private DcMotor backLeft;
    private DcMotor frontRight;
    private DcMotor armMotor1;
    private Servo drone;
    private Servo wrist;
    private Servo gripper;

    private static final int CAMERA_WIDTH = 1280; // width  of wanted camera resolution
    private static final int CAMERA_HEIGHT = 960; // height of wanted camera resolution

    private BlueObjectPipeline.ElementPosition DropPos;
    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        float forward;
        float strafe;
        float turn;
        float rotation;
        double denominator;
        int check = 2;

        armmotor2 = hardwareMap.get(DcMotor.class, "armmotor2");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        armMotor1 = hardwareMap.get(DcMotor.class, "armMotor1");
        drone = hardwareMap.get(Servo.class, "drone");
        wrist = hardwareMap.get(Servo.class, "wrist");
        gripper = hardwareMap.get(Servo.class, "gripper");

        drone.setPosition(0.75);

        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armmotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Put initialization blocks here.
        armmotor2.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        double powerDenominator = 0.5;

        //int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        //webcam = createWebcam(hardwareMap.get(WebcamName.class, "Webcam"), cameraMonitorViewId);

        //OpenCV Pipeline
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam"), cameraMonitorViewId);

        //OpenCV Pipeline
        BlueObjectPipeline myPipeline;
        webcam.setPipeline(myPipeline = new BlueObjectPipeline());

        // Webcam Streaming
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(CAMERA_WIDTH, CAMERA_HEIGHT, OpenCvCameraRotation.UPRIGHT); // Was Upright

            }

            @Override
            public void onError(int errorCode) {
                telemetry.addLine("Camera Opening Error !!!!!");
                telemetry.update();

                //This will be called if the camera could not be opened

            }
        });

        waitForStart();

        DropPos = myPipeline.getAnalysis();



        if (opModeIsActive()) {
            if (DropPos == BlueObjectPipeline.ElementPosition.LEFT) {
                check = 1;
            }
            if (DropPos == BlueObjectPipeline.ElementPosition.CENTER) {
                check = 2;
            }
            if (DropPos == BlueObjectPipeline.ElementPosition.RIGHT) {
                check = 3;
            }
            //movement(check);
            telemetry.addData("check", check);
            telemetry.update();




        }


    }
    public void movement(int check)
    {
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        if (check == 1)
        {
            //moves for 3 seconds
            frontLeft.setPower(0.7);
            frontRight.setPower(0.7);
            backLeft.setPower(0.7);
            backRight.setPower(0.7);
            sleep(3000);

            //turns 90* left
            frontLeft.setPower(-0.7);
            frontRight.setPower(0.7);
            backLeft.setPower(-0.7);
            backRight.setPower(0.7);
            sleep(2000);

        }

        if (check == 2)
        {
            //moves for 3 seconds
            frontLeft.setPower(0.7);
            frontRight.setPower(0.7);
            backLeft.setPower(0.7);
            backRight.setPower(0.7);
            sleep(3000);
        }

        if (check == 3)
        {
            //moves for 3 seconds
            frontLeft.setPower(0.7);
            frontRight.setPower(0.7);
            backLeft.setPower(0.7);
            backRight.setPower(0.7);
            sleep(3000);

            //turns 90* right
            frontLeft.setPower(0.7);
            frontRight.setPower(-0.7);
            backLeft.setPower(0.7);
            backRight.setPower(-0.7);
            sleep(2000);

        }
    }

}