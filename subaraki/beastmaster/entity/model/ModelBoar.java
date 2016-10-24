package subaraki.beastmaster.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBoar extends ModelBase {
	// fields
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg3;
	ModelRenderer leg4;
	ModelRenderer FrontBody;
	ModelRenderer Nose;
	ModelRenderer Mouth;
	ModelRenderer TuskR;
	ModelRenderer TuskL;
	ModelRenderer EarL;
	ModelRenderer EarR;

	public ModelBoar()
	{
		textureWidth = 65;
		textureHeight = 64;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4F, -7F, -8F, 8, 8, 8);
		head.setRotationPoint(0F, 12F, -6F);
		head.setTextureSize(65, 64);
		head.mirror = true;
		setRotation(head, 0.2094395F, 0F, 0F);
		body = new ModelRenderer(this, 28, 16);
		body.addBox(-5F, -7F, -7F, 10, 11, 8);
		body.setRotationPoint(0F, 10F, 7F);
		body.setTextureSize(65, 64);
		body.mirror = true;
		setRotation(body, 1.308997F, 0F, 0F);
		leg1 = new ModelRenderer(this, 0, 20);
		leg1.addBox(-1.9F, 0F, -2F, 4, 8, 4);
		leg1.setRotationPoint(-3F, 16F, 7F);
		leg1.setTextureSize(65, 64);
		leg1.mirror = true;
		setRotation(leg1, 0F, 0F, 0F);
		leg2 = new ModelRenderer(this, 0, 20);
		leg2.addBox(-2.1F, 0F, -2F, 4, 8, 4);
		leg2.setRotationPoint(3F, 16F, 7F);
		leg2.setTextureSize(65, 64);
		leg2.mirror = true;
		setRotation(leg2, 0F, 0F, 0F);
		leg3 = new ModelRenderer(this, 0, 20);
		leg3.addBox(-2F, 0F, -2F, 4, 8, 4);
		leg3.setRotationPoint(-3F, 16F, -5F);
		leg3.setTextureSize(65, 64);
		leg3.mirror = true;
		setRotation(leg3, 0F, 0F, 0F);
		leg4 = new ModelRenderer(this, 0, 20);
		leg4.addBox(-2F, 0F, -2F, 4, 8, 4);
		leg4.setRotationPoint(3F, 16F, -5F);
		leg4.setTextureSize(65, 64);
		leg4.mirror = true;
		setRotation(leg4, 0F, 0F, 0F);
		FrontBody = new ModelRenderer(this, 0, 45);
		FrontBody.addBox(-4F, 0F, -4F, 11, 10, 9);
		FrontBody.setRotationPoint(-1.5F, 6F, -4F);
		FrontBody.setTextureSize(65, 64);
		FrontBody.mirror = true;
		setRotation(FrontBody, 0F, 0F, 0F);
		Nose = new ModelRenderer(this, 0, 37);
		Nose.addBox(-1.5F, -3F, -12F, 3, 3, 4);
		Nose.setRotationPoint(0F, 0F, 0F);
		Nose.setTextureSize(65, 64);
		Nose.mirror = true;
		setRotation(Nose, 0F, 0F, 0F);
		Mouth = new ModelRenderer(this, 0, 32);
		Mouth.addBox(-1F, 0F, -3F, 2, 1, 4);
		Mouth.setRotationPoint(0F, -0.2F, -8F);
		Mouth.setTextureSize(65, 64);
		Mouth.mirror = true;
		setRotation(Mouth, 0F, 0F, 0F);
		TuskR = new ModelRenderer(this, 18, 37);
		TuskR.addBox(-0.5F, -2F, 0F, 1, 2, 1);
		TuskR.setRotationPoint(-1.5F, 0F, -10F);
		TuskR.setTextureSize(65, 64);
		TuskR.mirror = true;
		setRotation(TuskR, 0.209F, 0F, -0.5F);
		TuskL = new ModelRenderer(this, 22, 37);
		TuskL.addBox(-0.5F, -2F, 0F, 1, 2, 1);
		TuskL.setRotationPoint(1.5F, 0F, -10F);
		TuskL.setTextureSize(65, 64);
		TuskL.mirror = true;
		setRotation(TuskL, 0.209F, 0F, 0.5F);
		EarL = new ModelRenderer(this, 0, 0);
		EarL.addBox(-5F, -10.5F, -7F, 2, 3, 2);
		EarL.setRotationPoint(0F, 0F, 0F);
		EarL.setTextureSize(65, 64);
		EarL.mirror = true;
		setRotation(EarL, 0F, -0.6F, -0.5F);
		EarR = new ModelRenderer(this, 24, 0);
		EarR.addBox(3F, -10.5F, -7F, 2, 3, 2);
		EarR.setRotationPoint(0F, 0F, 0F);
		EarR.setTextureSize(65, 64);
		EarR.mirror = true;
		setRotation(EarR, 0F, 0.6F, 0.5F);
		
		Nose.addChild(TuskL);
		Nose.addChild(TuskR);
		Nose.addChild(Mouth);
		
		head.addChild(Nose);
		head.addChild(EarL);
		head.addChild(EarR);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		head.render(f5);
		body.render(f5);
		leg1.render(f5);
		leg2.render(f5);
		leg3.render(f5);
		leg4.render(f5);
		FrontBody.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
		
		this.Mouth.rotateAngleX = MathHelper.sin(ageInTicks/10f)/5f + 0.22f;
		
		this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leg2.rotateAngleX = MathHelper.cos((limbSwing * 0.6662F) + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.leg3.rotateAngleX = MathHelper.cos((limbSwing * 0.6662F) + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		
	}
}
