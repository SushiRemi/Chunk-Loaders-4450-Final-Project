/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chunkloaders.pkg4450.pkgfinal.project;

/**
 *
 * @author Ada
 */

import org.lwjgl.input.Mouse;

public class MouseMove {
    private final Camera camera;

    public MouseMove(Camera camera) {
        this.camera = camera;
    }

    public void update() {
        // Rotate the camera when mouse move
        float mouseYaw = Mouse.getDX() * 0.1f;
        float mousePitch = Mouse.getDY() * 0.1f;
        camera.rotate(mouseYaw, mousePitch);
    }
}