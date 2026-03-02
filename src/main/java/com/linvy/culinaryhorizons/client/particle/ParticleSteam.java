package com.linvy.culinaryhorizons.client.particle;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ParticleSteam extends EntityFX {

    private static final ResourceLocation[] STEAM_TEXTURES = new ResourceLocation[12];

    static {
        for (int i = 0; i < 12; i++) {
            STEAM_TEXTURES[i] = new ResourceLocation("culinaryhorizons", "textures/particle/steam_" + i + ".png");
        }
    }

    private final ResourceLocation texture;

    public ParticleSteam(World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y + 0.3D, z, motionX, motionY, motionZ);

        this.texture = STEAM_TEXTURES[this.rand.nextInt(12)];

        this.motionX = motionX;
        this.motionY = motionY + (this.rand.nextFloat() / 100.0F);
        this.motionZ = motionZ;

        this.particleScale = 3.0F;
        this.particleMaxAge = this.rand.nextInt(50) + 80;
        this.particleGravity = -15.0E-6F;

        this.particleRed = 1.0F;
        this.particleGreen = 1.0F;
        this.particleBlue = 1.0F;
        this.particleAlpha = 0.8F;

        this.noClip = false;
    }

    @Override
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ < this.particleMaxAge && this.particleAlpha > 0.0F) {
            this.motionX += this.rand.nextFloat() / 5000.0F * (this.rand.nextBoolean() ? 1 : -1);
            this.motionZ += this.rand.nextFloat() / 5000.0F * (this.rand.nextBoolean() ? 1 : -1);
            this.motionY -= this.particleGravity;

            this.moveEntity(this.motionX, this.motionY, this.motionZ);

            if (this.particleAge >= this.particleMaxAge - 60 && this.particleAlpha > 0.01F) {
                this.particleAlpha -= 0.02F;
            }
        } else {
            this.setDead();
        }
    }

    @Override
    public void renderParticle(Tessellator tessellator, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(this.texture);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, this.particleAlpha);
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        float x = (float)(this.prevPosX + (this.posX - this.prevPosX) * partialTicks - interpPosX);
        float y = (float)(this.prevPosY + (this.posY - this.prevPosY) * partialTicks - interpPosY);
        float z = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * partialTicks - interpPosZ);
        float scale = 0.1F * this.particleScale;

        tessellator.startDrawingQuads();
        tessellator.setBrightness(240);
        tessellator.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha);

        tessellator.addVertexWithUV(x - rotationX * scale - rotationXY * scale, y - rotationZ * scale, z - rotationYZ * scale - rotationXZ * scale, 0, 1);
        tessellator.addVertexWithUV(x - rotationX * scale + rotationXY * scale, y + rotationZ * scale, z - rotationYZ * scale + rotationXZ * scale, 1, 1);
        tessellator.addVertexWithUV(x + rotationX * scale + rotationXY * scale, y + rotationZ * scale, z + rotationYZ * scale + rotationXZ * scale, 1, 0);
        tessellator.addVertexWithUV(x + rotationX * scale - rotationXY * scale, y - rotationZ * scale, z + rotationYZ * scale - rotationXZ * scale, 0, 0);

        tessellator.draw();

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDepthMask(true);
    }

    @Override
    public int getFXLayer() {
        return 3;
    }
}
