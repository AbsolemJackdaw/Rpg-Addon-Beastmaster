package subaraki.beastmaster.item.armor.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;

public class ModelBeastArmor extends ModelBiped {
	// fields
	public ModelRenderer SpaulderL;
	public ModelRenderer SpaulderR;
	public ModelRenderer Horn1;
	public ModelRenderer Horn2;
	public ModelRenderer Horn3;
	public ModelRenderer Horn4;

	public ModelBeastArmor(float par1) {

		super(par1, 0, 65, 64);

		textureWidth = 65;
		textureHeight = 64;

		SpaulderL = new ModelRenderer(this, 0, 38);
		SpaulderL.addBox(0F, -3F, -2.5F, 5, 3, 5, par1);
		SpaulderL.setRotationPoint(0F, 0F, 0F);
		SpaulderL.setTextureSize(65, 64);
		SpaulderL.mirror = true;
		setRotation(SpaulderL, 0F, 0F, 0.1745329F);
		SpaulderR = new ModelRenderer(this, 0, 46);
		SpaulderR.addBox(-5F, -3F, -2.5F, 5, 3, 5, par1);
		SpaulderR.setRotationPoint(0F, 0F, 0F);
		SpaulderR.setTextureSize(65, 64);
		SpaulderR.mirror = true;
		setRotation(SpaulderR, 0F, 0F, -0.1745329F);
		Horn1 = new ModelRenderer(this, 0, 34);
		Horn1.addBox(4F, -7F, 1F, 1, 2, 2, par1 / 2);
		Horn1.setRotationPoint(0F, 0F, 0F);
		Horn1.setTextureSize(65, 64);
		Horn1.mirror = true;
		setRotation(Horn1, 0F, 0F, 0F);
		Horn2 = new ModelRenderer(this, 0, 32);
		Horn2.addBox(1F, -7F, 4F, 3, 1, 1, par1 / 2);
		Horn2.setRotationPoint(0F, 0F, 0F);
		Horn2.setTextureSize(65, 64);
		Horn2.mirror = true;
		setRotation(Horn2, 0F, 0.7853982F, 0.1745329F);
		Horn3 = new ModelRenderer(this, 0, 34);
		Horn3.addBox(-5F, -7F, 1F, 1, 2, 2, par1 / 2);
		Horn3.setRotationPoint(0F, 0F, 0F);
		Horn3.setTextureSize(65, 64);
		Horn3.mirror = true;
		setRotation(Horn3, 0F, 0F, 0F);
		Horn4 = new ModelRenderer(this, 0, 32);
		Horn4.addBox(-4F, -7F, 4F, 3, 1, 1, par1 / 2);
		Horn4.setRotationPoint(0F, 0F, 0F);
		Horn4.setTextureSize(65, 64);
		Horn4.mirror = true;
		setRotation(Horn4, 0F, -0.7853982F, -0.1745329F);
	
		this.bipedHead.addChild(Horn1);
		this.bipedHead.addChild(Horn2);
		this.bipedHead.addChild(Horn3);
		this.bipedHead.addChild(Horn4);
		this.bipedLeftArm.addChild(SpaulderL);
		this.bipedRightArm.addChild(SpaulderR);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}