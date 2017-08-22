package subaraki.beastmaster.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelRooster extends ModelBase
{
	//fields
	ModelRenderer head;
	ModelRenderer bill;
	ModelRenderer chin;
	ModelRenderer body;
	ModelRenderer rightLeg;
	ModelRenderer leftLeg;
	ModelRenderer rightWing;
	ModelRenderer leftWing;
	ModelRenderer combfront;
	ModelRenderer combmiddle;
	ModelRenderer combback;
	ModelRenderer tail1;
	ModelRenderer tail2;
	ModelRenderer tail3;
	ModelRenderer tail4;
	ModelRenderer tail5;
	ModelRenderer tail6;

	public ModelRooster()
	{
		textureWidth = 64;
		textureHeight = 32;

		ModelChicken e;
		byte b0 = 16;
		this.head = new ModelRenderer(this, 0, 0);
		this.head.addBox(-2.0F, -6.0F, -2.0F, 4, 6, 3, 0.0F);
		this.head.setRotationPoint(0.0F, (float)(-1 + b0), -4.0F);
		this.bill = new ModelRenderer(this, 14, 0);
		this.bill.addBox(-2.0F, -4.0F, -4.0F, 4, 2, 2, 0.0F);
		this.bill.setRotationPoint(0.0F, (float)(-1 + b0), -4.0F);
		this.chin = new ModelRenderer(this, 14, 4);
		this.chin.addBox(-1.0F, -2.0F, -3.0F, 2, 2, 2, 0.0F);
		this.chin.setRotationPoint(0.0F, (float)(-1 + b0), -4.0F);
		this.body = new ModelRenderer(this, 0, 9);
		this.body.addBox(-3.0F, -4.0F, -3.0F, 6, 8, 6, 0.0F);
		this.body.setRotationPoint(0.0F, (float)b0, 0.0F);
		this.rightLeg = new ModelRenderer(this, 26, 0);
		this.rightLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3);
		this.rightLeg.setRotationPoint(-2.0F, (float)(3 + b0), 1.0F);
		this.leftLeg = new ModelRenderer(this, 26, 0);
		this.leftLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3);
		this.leftLeg.setRotationPoint(1.0F, (float)(3 + b0), 1.0F);
		this.rightWing = new ModelRenderer(this, 24, 13);
		this.rightWing.addBox(0.0F, 0.0F, -3.0F, 1, 4, 6);
		this.rightWing.setRotationPoint(-4.0F, (float)(-3 + b0), 0.0F);
		this.leftWing = new ModelRenderer(this, 24, 13);
		this.leftWing.addBox(-1.0F, 0.0F, -3.0F, 1, 4, 6);
		this.leftWing.setRotationPoint(4.0F, (float)(-3 + b0), 0.0F);

		this.combfront = new ModelRenderer(this, 38, 0);
		this.combfront.addBox(-0.5F, -7F, -1.7F, 1, 1, 1);
		this.combfront.setRotationPoint(0F, 0F, 0F);
		this.combfront.setTextureSize(64, 32);
		this.combmiddle = new ModelRenderer(this, 38, 0);
		this.combmiddle.addBox(-0.5F, -7.5F, -1F, 1, 2, 1);
		this.combmiddle.setRotationPoint(0F, 0F, 0F);
		this.combmiddle.setTextureSize(64, 32);
		this.combback = new ModelRenderer(this, 38, 0);
		this.combback.addBox(-0.5F, -7F, -0.2F, 1, 1, 1);
		this.combback.setRotationPoint(0F, 0F, 0F);
		this.combback.setTextureSize(64, 32);
		this.tail1 = new ModelRenderer(this, 38, 13);
		this.tail1.addBox(-0.5F, -5F, 4F, 1, 4, 1);
		this.tail1.setRotationPoint(0F, 16F, 0F);
		this.tail1.setTextureSize(64, 32);
		this.tail2 = new ModelRenderer(this, 38, 13);
		this.tail2.addBox(0.5F, -6F, 4F, 1, 5, 1);
		this.tail2.setRotationPoint(0F, 16F, 0F);
		this.tail2.setTextureSize(64, 32);
		this.tail3 = new ModelRenderer(this, 42, 13);
		this.tail3.addBox(-1.5F, -5.5F, 4F, 1, 5, 1);
		this.tail3.setRotationPoint(0F, 16F, 0F);
		this.tail3.setTextureSize(64, 32);
		this.tail4 = new ModelRenderer(this, 38, 13);
		this.tail4.addBox(0.5F, -6F, 5F, 1, 1, 1);
		this.tail4.setRotationPoint(0F, 16F, 0F);
		this.tail4.setTextureSize(64, 32);
		this.tail5 = new ModelRenderer(this, 42, 13);
		this.tail5.addBox(-0.5F, -6F, 5F, 1, 5, 1);
		this.tail5.setRotationPoint(0F, 16F, 0F);
		this.tail5.setTextureSize(64, 32);
		this.tail6 = new ModelRenderer(this, 42, 13);
		this.tail6.addBox(-0.5F, -6F, 6F, 1, 1, 1);
		this.tail6.setRotationPoint(0F, 16F, 0F);
		this.tail6.setTextureSize(64, 32);

		head.addChild(combback);
		head.addChild(combfront);
		head.addChild(combmiddle);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		head.render(f5);
		bill.render(f5);
		chin.render(f5);
		body.render(f5);
		rightLeg.render(f5);
		leftLeg.render(f5);
		rightWing.render(f5);
		leftWing.render(f5);
		tail1.render(f5);
		tail2.render(f5);
		tail3.render(f5);
		tail4.render(f5);
		tail5.render(f5);
		tail6.render(f5);
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
	{
		this.head.rotateAngleX = par5 / (180F / (float)Math.PI);
		this.head.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.bill.rotateAngleX = this.head.rotateAngleX;
		this.bill.rotateAngleY = this.head.rotateAngleY;
		this.chin.rotateAngleX = this.head.rotateAngleX;
		this.chin.rotateAngleY = this.head.rotateAngleY;
		this.body.rotateAngleX = ((float)Math.PI / 2F);
		this.rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.rightWing.rotateAngleZ = par3;
		this.leftWing.rotateAngleZ = -par3;
	}
}
