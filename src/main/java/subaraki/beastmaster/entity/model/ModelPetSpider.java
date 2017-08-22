package subaraki.beastmaster.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelPetSpider extends ModelBase {
	// fields

	public ModelRenderer head;
	public ModelRenderer cephalotorax;
	public ModelRenderer abdomen;
	public ModelRenderer leg8;
	public ModelRenderer leg8ext;
	public ModelRenderer leg6;
	public ModelRenderer leg6ext;
	public ModelRenderer leg4;
	public ModelRenderer leg4ext;
	public ModelRenderer leg2;
	public ModelRenderer leg2ext;
	public ModelRenderer leg7;
	public ModelRenderer leg7ext;
	public ModelRenderer leg5;
	public ModelRenderer leg5ext;
	public ModelRenderer leg3;
	public ModelRenderer leg3ext;
	public ModelRenderer leg1;
	public ModelRenderer leg1ext;
	public ModelRenderer cheliceraLeft;
	public ModelRenderer cheliceraRight;

	public ModelPetSpider() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this, 32, 4);
		head.addBox(-4F, -4F, -8F, 8, 8, 8);
		head.setRotationPoint(0F, 12F, -3F);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		cephalotorax = new ModelRenderer(this, 0, 0);
		cephalotorax.addBox(-3F, -2F, -4F, 6, 6, 6);
		cephalotorax.setRotationPoint(0F, 12F, 0F);
		cephalotorax.setTextureSize(64, 64);
		cephalotorax.mirror = true;
		setRotation(cephalotorax, -0.1745329F, 0F, 0F);
		abdomen = new ModelRenderer(this, 0, 12);
		abdomen.addBox(-5F, -1F, -7F, 10, 8, 12);
		abdomen.setRotationPoint(0F, 12F, 9F);
		abdomen.setTextureSize(64, 64);
		abdomen.mirror = true;
		setRotation(abdomen, -0.296706F, 0F, 0F);
		leg8 = new ModelRenderer(this, 18, 0);
		leg8.addBox(-1F, -1F, -1F, 16, 2, 2);
		leg8.setRotationPoint(4F, 12F, -1F);
		leg8.setTextureSize(64, 64);
		leg8.mirror = true;
		setRotation(leg8, 0F, 0.5759587F, 0.1919862F);
		leg8ext = new ModelRenderer(this, 0, 35);
		leg8ext.addBox(-1.001F, -1F, -1.001F, 10, 2, 2);
		leg8ext.setRotationPoint(15F, 0.5F, 0F);
		leg8ext.setTextureSize(64, 64);
		leg8ext.mirror = true;
		setRotation(leg8ext, 0F, 0F, 0.1919862F * 4);
		leg6 = new ModelRenderer(this, 18, 0);
		leg6.addBox(-1F, -1F, -1F, 16, 2, 2);
		leg6.setRotationPoint(4F, 12F, 0F);
		leg6.setTextureSize(64, 64);
		leg6.mirror = true;
		setRotation(leg6, 0F, 0.2792527F, 0.1919862F);
		leg6ext = new ModelRenderer(this, 0, 35);
		leg6ext.addBox(-1.001F, -1F, -1.001F, 10, 2, 2);
		leg6ext.setRotationPoint(15F, 0.5F, 0F);
		leg6ext.setTextureSize(64, 64);
		leg6ext.mirror = true;
		setRotation(leg6ext, 0F, 0, 0.1919862F * 4);
		leg4 = new ModelRenderer(this, 18, 0);
		leg4.addBox(-1F, -1F, -1F, 16, 2, 2);
		leg4.setRotationPoint(4F, 12F, 1F);
		leg4.setTextureSize(64, 64);
		leg4.mirror = true;
		setRotation(leg4, 0F, -0.2792527F, 0.1919862F);
		leg4ext = new ModelRenderer(this, 0, 35);
		leg4ext.addBox(-1.001F, -1F, -1.001F, 10, 2, 2);
		leg4ext.setRotationPoint(15F, 0.5F, 0F);
		leg4ext.setTextureSize(64, 64);
		leg4ext.mirror = true;
		setRotation(leg4ext, 0F, 0F, 0.1919862F * 4);
		leg2 = new ModelRenderer(this, 18, 0);
		leg2.addBox(-1F, -1F, -1F, 16, 2, 2);
		leg2.setRotationPoint(4F, 12F, 2F);
		leg2.setTextureSize(64, 64);
		leg2.mirror = true;
		setRotation(leg2, 0F, -0.5759587F, 0.1919862F);
		leg2ext = new ModelRenderer(this, 0, 35);
		leg2ext.addBox(-1.001F, -1F, -1.001F, 10, 2, 2);
		leg2ext.setRotationPoint(15F, 0.5F, 0F);
		leg2ext.setTextureSize(64, 64);
		leg2ext.mirror = true;
		setRotation(leg2ext, 0F, 0F, 0.1919862F * 4);
		leg7 = new ModelRenderer(this, 18, 0);
		leg7.addBox(-15F, -1F, -1F, 16, 2, 2);
		leg7.setRotationPoint(-3F, 12F, -1F);
		leg7.setTextureSize(64, 64);
		leg7.mirror = true;
		setRotation(leg7, 0F, -0.5759587F, -0.1919862F);
		leg7ext = new ModelRenderer(this, 0, 35);
		leg7ext.addBox(-1.001F, -1F, -1.001F, 10, 2, 2);
		leg7ext.setRotationPoint(-21F, 6F, 0F);
		leg7ext.setTextureSize(64, 64);
		leg7ext.mirror = true;
		setRotation(leg7ext, 0F, 0F, -0.1919862F * 4);
		leg5 = new ModelRenderer(this, 18, 0);
		leg5.addBox(-15F, -1F, -1F, 16, 2, 2);
		leg5.setRotationPoint(-4F, 12F, 0F);
		leg5.setTextureSize(64, 64);
		leg5.mirror = true;
		setRotation(leg5, 0F, -0.2792527F, -0.1919862F);
		leg5ext = new ModelRenderer(this, 0, 35);
		leg5ext.addBox(-1.001F, -1F, -1.001F, 10, 2, 2);
		leg5ext.setRotationPoint(-21F, 6F, 0F);
		leg5ext.setTextureSize(64, 64);
		leg5ext.mirror = true;
		setRotation(leg5ext, 0F, 0F, -0.1919862F * 4);
		leg3 = new ModelRenderer(this, 18, 0);
		leg3.addBox(-15F, -1F, -1F, 16, 2, 2);
		leg3.setRotationPoint(-4F, 12F, 1F);
		leg3.setTextureSize(64, 64);
		leg3.mirror = true;
		setRotation(leg3, 0F, 0.2792527F, -0.1919862F);
		leg3ext = new ModelRenderer(this, 0, 35);
		leg3ext.addBox(-1.001F, -1F, -1.001F, 10, 2, 2);
		leg3ext.setRotationPoint(-21F, 6F, 0F);
		leg3ext.setTextureSize(64, 64);
		leg3ext.mirror = true;
		setRotation(leg3ext, 0F, 0F, -0.1919862F * 4);
		leg1 = new ModelRenderer(this, 18, 0);
		leg1.addBox(-15F, -1F, -1F, 16, 2, 2);
		leg1.setRotationPoint(-4F, 12F, 2F);
		leg1.setTextureSize(64, 64);
		leg1.mirror = true;
		setRotation(leg1, 0F, 0.5759587F, -0.1919862F);
		leg1ext = new ModelRenderer(this, 0, 35);
		leg1ext.addBox(-1.001F, -1F, -1.001F, 10, 2, 2);
		leg1ext.setRotationPoint(-21F, 6F, 0F);
		leg1ext.setTextureSize(64, 64);
		leg1ext.mirror = true;
		setRotation(leg1ext, 0F, 0F, -0.1919862F * 4);
		cheliceraLeft = new ModelRenderer(this, 0, 0);
		cheliceraLeft.addBox(0F, 0F, 0F, 2, 1, 1);
		cheliceraLeft.setRotationPoint(-3F, 2F, -9F);
		cheliceraLeft.setTextureSize(64, 64);
		cheliceraLeft.mirror = true;
		setRotation(cheliceraLeft, 0F, 0F, 0F);
		cheliceraRight = new ModelRenderer(this, 0, 0);
		cheliceraRight.addBox(0F, 0F, 0F, 2, 1, 1);
		cheliceraRight.setRotationPoint(3F, 3.1F, -9F);
		cheliceraRight.setTextureSize(64, 64);
		cheliceraRight.mirror = true;
		setRotation(cheliceraRight, 0F, 0F, 3.2f);

		leg8.addChild(leg8ext);
		leg7.addChild(leg7ext);
		leg6.addChild(leg6ext);
		leg5.addChild(leg5ext);
		leg4.addChild(leg4ext);
		leg3.addChild(leg3ext);
		leg2.addChild(leg2ext);
		leg1.addChild(leg1ext);
		head.addChild(cheliceraLeft);
		head.addChild(cheliceraRight);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,float f4, float f5) {
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	
		GlStateManager.translate(0, -0.2f, 0);
		head.render(f5);
		cephalotorax.render(f5);
		abdomen.render(f5);
		leg8.render(f5);
		leg6.render(f5);
		leg4.render(f5);
		leg2.render(f5);
		leg7.render(f5);
		leg5.render(f5);
		leg3.render(f5);
		leg1.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
	super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

		this.head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
		this.head.rotateAngleX = headPitch / (180F / (float) Math.PI);

		this.cheliceraLeft.rotateAngleY = MathHelper.sin(ageInTicks/10f)/5f + 0.22f;
		this.cheliceraRight.rotateAngleY = MathHelper.sin(ageInTicks/10f)/5f + 0.22f;
		
		float var8 = ((float) Math.PI / 4F);
		this.leg1.rotateAngleZ = -var8;
		this.leg2.rotateAngleZ = var8;
		this.leg3.rotateAngleZ = -var8 * 0.74F;
		this.leg4.rotateAngleZ = var8 * 0.74F;
		this.leg5.rotateAngleZ = -var8 * 0.74F;
		this.leg6.rotateAngleZ = var8 * 0.74F;
		this.leg7.rotateAngleZ = -var8;
		this.leg8.rotateAngleZ = var8;
		float var9 = 0F;
		float var10 = 0.3926991F;
		this.leg1.rotateAngleY = (var10 * 2.0F) + var9;
		this.leg2.rotateAngleY = (-var10 * 2.0F) - var9;
		
		this.leg3.rotateAngleY = (var10 * 1.0F) + var9;
		this.leg4.rotateAngleY = (-var10 * 1.0F) - var9;
		
		this.leg5.rotateAngleY = (-var10 * 1.0F) + var9;
		this.leg6.rotateAngleY = (var10 * 1.0F) - var9;
		
		this.leg7.rotateAngleY = (-var10 * 2.0F) + var9;
		this.leg8.rotateAngleY = (var10 * 2.0F) - var9;
		
		float var11 = -(MathHelper.cos((limbSwing * 0.6662F * 2.0F)) * 0.4F)* limbSwingAmount;
		float var12 = -(MathHelper.cos((limbSwing * 0.6662F * 2.0F)+ (float) Math.PI) * 0.4F)* limbSwingAmount;
		float var13 = -(MathHelper.cos((limbSwing * 0.6662F * 2.0F)+ ((float) Math.PI / 2F)) * 0.4F)* limbSwingAmount;
		float var14 = -(MathHelper.cos((limbSwing * 0.6662F * 2.0F)+ (((float) Math.PI * 3F) / 2F)) * 0.4F)* limbSwingAmount;
		
		float var15 = Math.abs(MathHelper.sin((limbSwing * 0.6662F) + 0.0F) * 0.4F)* limbSwingAmount;
		float var16 = Math.abs(MathHelper.sin((limbSwing * 0.6662F)+ (float) Math.PI) * 0.4F)* limbSwingAmount;
		float var17 = Math.abs(MathHelper.sin((limbSwing * 0.6662F)+ ((float) Math.PI / 2F)) * 0.4F)* limbSwingAmount;
		float var18 = Math.abs(MathHelper.sin((limbSwing * 0.6662F)+ (((float) Math.PI * 3F) / 2F)) * 0.4F)* limbSwingAmount;
		
		this.leg1.rotateAngleY += var11;
		this.leg2.rotateAngleY += -var11;
		this.leg3.rotateAngleY += var12;
		this.leg4.rotateAngleY += -var12;
		this.leg5.rotateAngleY += var13;
		this.leg6.rotateAngleY += -var13;
		this.leg7.rotateAngleY += var14;
		this.leg8.rotateAngleY += -var14;
		this.leg1.rotateAngleZ += var15;
		this.leg2.rotateAngleZ += -var15;
		this.leg3.rotateAngleZ += var16;
		this.leg4.rotateAngleZ += -var16;
		this.leg5.rotateAngleZ += var17;
		this.leg6.rotateAngleZ += -var17;
		this.leg7.rotateAngleZ += var18;
		this.leg8.rotateAngleZ += -var18;
	}
}
