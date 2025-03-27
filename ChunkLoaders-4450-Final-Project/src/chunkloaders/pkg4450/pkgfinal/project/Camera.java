/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chunkloaders.pkg4450.pkgfinal.project;

//importing lwjgl tools

import static org.lwjgl.opengl.GL11.*;


/**
 *
 * @author Julianne, Ada
 */


import org.lwjgl.util.vector.Vector3f;

// Camera controller
public class Camera {
    // Pos x,y,z
    protected Vector3f position;
    protected float yaw;
    protected float pitch;

    public Camera(float x, float y, float z) {
        this.position = new Vector3f(x, y, z);
    }
    
    // Code from Canvas
    public void lookThrough() {
        glRotatef(pitch, 1.0f, 0.0f, 0.0f);
        glRotatef(yaw, 0.0f, 1.0f, 0.0f);
        glTranslatef(position.x, position.y, position.z);
    }

    public void rotate(float deltaYaw, float deltaPitch) {
        yaw += deltaYaw;
        pitch -= deltaPitch;

        if (pitch > 90) pitch = 90;
        if (pitch < -90) pitch = -90;
    }

    public void moveForward(float distance) {
        position.x -= distance * (float) Math.sin(Math.toRadians(yaw));
        position.z += distance * (float) Math.cos(Math.toRadians(yaw));
    }

    public void moveBack(float distance) {
        position.x += distance * (float) Math.sin(Math.toRadians(yaw));
        position.z -= distance * (float) Math.cos(Math.toRadians(yaw));
    }

    public void strafeLeft(float distance) {
        position.x -= distance * (float) Math.sin(Math.toRadians(yaw - 90));
        position.z += distance * (float) Math.cos(Math.toRadians(yaw - 90));
    }

    public void strafeRight(float distance) {
        position.x -= distance * (float) Math.sin(Math.toRadians(yaw + 90));
        position.z += distance * (float) Math.cos(Math.toRadians(yaw + 90));
    }

    public void moveUp(float distance) {
        position.y += distance;
    }

    public void moveDown(float distance) {
        position.y -= distance;
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }
}