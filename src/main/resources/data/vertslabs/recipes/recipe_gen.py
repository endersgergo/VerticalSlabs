types = ["oak", "birch", "spruce", "jungle", "acacia", "dark_oak", "crimson", "warped", "stone",
         "smooth_stone", "cobblestone", "mossy_cobblestone", "stone_brick",
         "mossy_stone_brick", "andesite", "polished_andesite", "diorite",
         "polished_diorite", "granite", "polished_granite", "sandstone",
         "cut_sandstone", "smooth_sandstone", "red_sandstone", "cut_red_sandstone",
         "smooth_red_sandstone", "brick", "prismarine", "prismarine_brick",
         "dark_prismarine", "nether_brick", "red_nether_brick", "quartz",
         "smooth_quartz", "purpur", "end_stone_brick", "blackstone",
         "polished_blackstone", "polished_blackstone_brick"]

for t in types:
    f = open(t + "_slab.json", 'w')
    f.write(
    """
{{
  "type": "minecraft:crafting_shapeless",
  "group": "slab",
  "ingredients": [
    {{
      "item": "vertslabs:vertical_{}_slab"
    }}
  ],
  "result": {{
    "item": "minecraft:{}_slab"
  }}
}}
    """.format(t, t)
    )
    f.close()
