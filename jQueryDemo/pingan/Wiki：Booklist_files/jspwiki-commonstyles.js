var WikiReflection = {
	render : function(b, a) {
		$ES("*[class^=reflection]", b).each(function(c) {
			var d = c.className.split("-");
			$ES("img", c).each(function(e) {
				Reflection.add(e, d[1], d[2])
			})
		})
	}
};
Wiki.addPageRender(WikiReflection);
var Reflection = {
	options : {
		height : 0.33,
		opacity : 0.5
	},
	add : function(e, j, h) {
		j = (j) ? j / 100 : this.options.height;
		h = (h) ? h / 100 : this.options.opacity;
		var b = new Element("div").injectAfter(e).adopt(e), i = e.width, d = e.height, c = Math
				.floor(d * j);
		b.className = e.className.replace(/\breflection\b/, "");
		b.style.cssText = e.backupStyle = e.style.cssText;
		b.setStyles({
			width : e.width,
			height : d + c
		});
		e.style.cssText = "vertical-align: bottom";
		if (window.ie) {
			new Element(
					"img",
					{
						src : e.src,
						styles : {
							width : i,
							marginBottom : "-" + (d - c) + "px",
							filter : "flipv progid:DXImageTransform.Microsoft.Alpha(opacity="
									+ (h * 100)
									+ ", style=1, finishOpacity=0, startx=0, starty=0, finishx=0, finishy="
									+ (j * 100) + ")"
						}
					}).inject(b)
		} else {
			var a = new Element("canvas", {
				width : i,
				height : c,
				styles : {
					width : i,
					height : c
				}
			}).inject(b);
			if (!a.getContext) {
				return
			}
			var k = a.getContext("2d");
			k.save();
			k.translate(0, d - 1);
			k.scale(1, -1);
			k.drawImage(e, 0, 0, i, d);
			k.restore();
			k.globalCompositeOperation = "destination-out";
			var f = k.createLinearGradient(0, 0, 0, c);
			f.addColorStop(0, "rgba(255, 255, 255, " + (1 - h) + ")");
			f.addColorStop(1, "rgba(255, 255, 255, 1.0)");
			k.fillStyle = f;
			k.rect(0, 0, i, c);
			k.fill()
		}
	}
};
var WikiAccordion = {
	render : function(d, c) {
		var a = new Element("div", {
			"class" : "toggle"
		}), b = new Element("div", {
			"class" : "collapseBullet"
		});
		$ES(".accordion, .tabbedAccordion, .leftAccordion, .rightAccordion", d)
				.each(
						function(e) {
							var g = [], f = [], h = false;
							if (e.hasClass("tabbedAccordion")) {
								h = new Element("div", {
									"class" : "menu top"
								}).injectBefore(e)
							} else {
								if (e.hasClass("leftAccordion")) {
									h = new Element("div", {
										"class" : "menu left"
									}).injectBefore(e)
								} else {
									if (e.hasClass("rightAccordion")) {
										h = new Element("div", {
											"class" : "menu right"
										}).injectBefore(e)
									}
								}
							}
							e.getChildren().each(
									function(j) {
										if (!j.className.test("^tab-")) {
											return
										}
										var k = j.className.substr(4)
												.deCamelize(), i = a.clone()
												.appendText(k);
										h ? i.inject(h) : b.clone().injectTop(
												i.injectBefore(j));
										g.push(i);
										f.push(j.addClass("tab"))
									});
							new Accordion(g, f, {
								height : true,
								alwaysHide : !h,
								onComplete : function() {
									var i = $(this.elements[this.previous]);
									if (i.offsetHeight > 0) {
										i.setStyle("height", "auto")
									}
								},
								onActive : function(j, k) {
									j.addClass("active");
									var i = j.getFirst();
									if (i) {
										i.setProperties({
											title : "collapse".localize(),
											"class" : "collapseOpen"
										}).setHTML("-")
									}
									k.addClass("active")
								},
								onBackground : function(j, k) {
									k.setStyle("height", k.offsetHeight);
									j.removeClass("active");
									var i = j.getFirst();
									if (i) {
										i.setProperties({
											title : "expand".localize(),
											"class" : "collapseClose"
										}).setHTML("+")
									}
									k.removeClass("active")
								}
							})
						});
		b = a = null
	}
};
Wiki.addPageRender(WikiAccordion);
var RoundedCorners = {
	$Top : {
		y : [ {
			margin : "5px",
			height : "1px",
			borderSide : "0",
			borderTop : "1px"
		}, {
			margin : "3px",
			height : "1px",
			borderSide : "2px"
		}, {
			margin : "2px",
			height : "1px",
			borderSide : "1px"
		}, {
			margin : "1px",
			height : "2px",
			borderSide : "1px"
		} ],
		s : [ {
			margin : "2px",
			height : "1px",
			borderSide : "0",
			borderTop : "1px"
		}, {
			margin : "1px",
			height : "1px",
			borderSide : "1px"
		} ],
		b : [ {
			margin : "8px",
			height : "1px",
			borderSide : "0",
			borderTop : "1px"
		}, {
			margin : "6px",
			height : "1px",
			borderSide : "2px"
		}, {
			margin : "4px",
			height : "1px",
			borderSide : "1px"
		}, {
			margin : "3px",
			height : "1px",
			borderSide : "1px"
		}, {
			margin : "2px",
			height : "1px",
			borderSide : "1px"
		}, {
			margin : "1px",
			height : "3px",
			borderSide : "1px"
		} ]
	},
	$registry : {},
	register : function(a, b) {
		this.$registry[a] = b;
		return this
	},
	render : function(d, b) {
		this.$Bottom = {};
		for ( var c in this.$Top) {
			this.$Bottom[c] = this.$Top[c].slice(0).reverse()
		}
		for ( var a in this.$registry) {
			var f = $$(a), e = this.$registry[a];
			this.exec(f, e[0], e[1], e[2], e[3])
		}
		$ES("*[class^=roundedCorners]", d).each(function(g) {
			var h = g.className.split("-");
			if (h.length >= 2) {
				this.exec([ g ], h[1], h[2], h[3], h[4])
			}
		}, this)
	},
	exec : function(d, b, a, g, e) {
		b = (b || "yyyy") + "nnnn";
		a = new Color(a) || "transparent";
		g = new Color(g);
		e = new Color(e);
		var f = b.split("");
		d.each(function(j) {
			if (j.$passed) {
				return
			}
			var i = this.addCorner(this.$Top, f[0], f[1], a, g, j), c = this
					.addCorner(this.$Bottom, f[2], f[3], a, g, j);
			if (i || c) {
				this.addBody(j, a, g);
				if (i) {
					var h = j.getStyle("padding-top").toInt();
					i.setStyle("margin-top", h - i.getChildren().length);
					j.setStyle("padding-top", 0);
					i.injectTop(j)
				}
				if (c) {
					var h = j.getStyle("padding-bottom").toInt();
					c.setStyle("margin-bottom", h - c.getChildren().length);
					j.setStyle("padding-bottom", 0);
					j.adopt(c)
				}
			}
			if (g) {
				j.setStyle("border", "none")
			}
			j.$passed = true
		}, this);
		top = bottom = null
	},
	getTemplate : function(c, a) {
		var b = false;
		if (a != "nn") {
			for ( var d in c) {
				if (a.contains(d)) {
					b = c[d];
					break
				}
			}
		}
		return b
	},
	addCorner : function(h, d, i, f, e, b) {
		h = this.getTemplate(h, d + i);
		if (!h) {
			return false
		}
		var g = b.getStyle("padding-left").toInt(), a = b.getStyle(
				"padding-right").toInt();
		var c = new Element("b", {
			"class" : "roundedCorners",
			styles : {
				display : "block",
				"margin-left" : -1 * g,
				"margin-right" : -1 * a
			}
		});
		h.each(function(j) {
			var k = new Element("div", {
				styles : {
					height : j.height,
					overflow : "hidden",
					"border-width" : "0",
					"background-color" : f.hex
				}
			});
			if (e.hex) {
				k.setStyles({
					"border-color" : e.hex,
					"border-style" : "solid"
				});
				if (j.borderTop) {
					k.setStyles({
						"border-top-width" : j.borderTop,
						height : "0"
					})
				}
			}
			if (d != "n") {
				k.setStyle("margin-left", j.margin)
			}
			if (i != "n") {
				k.setStyle("margin-right", j.margin)
			}
			if (e.hex) {
				k.setStyles({
					"border-left-width" : (d == "n") ? "1px" : j.borderSide,
					"border-right-width" : (i == "n") ? "1px" : j.borderSide
				})
			}
			c.adopt(k)
		});
		return c
	},
	addBody : function(g, b, d) {
		var e = g.getStyle("padding-left").toInt(), f = g.getStyle(
				"padding-right").toInt();
		var a = new Element("div", {
			styles : {
				overflow : "hidden",
				"margin-left" : -1 * e,
				"margin-right" : -1 * f,
				"padding-left" : (e == 0) ? 4 : e,
				"padding-right" : (f == 0) ? 4 : f,
				"background-color" : b.hex
			}
		}).wrapChildren(g);
		if (d.hex) {
			var c = "1px solid " + d.hex;
			a.setStyles({
				"border-left" : c,
				"border-right" : c
			})
		}
	}
};
Wiki.addPageRender(RoundedCorners);
var WikiTips = {
	render : function(c, b) {
		var a = [];
		$ES("*[class^=tip]", c)
				.each(
						function(f) {
							var g = f.className.split("-");
							if (g.length <= 0 || g[0] != "tip") {
								return
							}
							f.className = "tip";
							var d = new Element("span").wrapChildren(f).hide(), e = (g[1]) ? g[1]
									.deCamelize()
									: "tip.default.title".localize();
							a.push(new Element("span", {
								"class" : "tip-anchor",
								title : e + "::" + d.innerHTML
							}).setHTML(e).inject(f))
						});
		if (a.length > 0) {
			new Tips(a, {
				className : "tip",
				Xfixed : true
			})
		}
	}
};
Wiki.addPageRender(WikiTips);
var WikiColumns = {
	render : function(c, b) {
		var a = [];
		$ES("*[class^=columns]", c).each(function(d) {
			var e = d.className.split("-");
			d.className = "columns";
			WikiColumns.buildColumns(d, e[1] || "auto")
		})
	},
	buildColumns : function(c, b) {
		var f = $ES("hr", c);
		if (!f || f.length == 0) {
			return
		}
		var e = f.length + 1;
		b = (b == "auto") ? 98 / e + "%" : b / e + "px";
		var d = new Element("div", {
			"class" : "col",
			styles : {
				width : b
			}
		}), a = d.clone().injectTop(c), g;
		while (g = a.nextSibling) {
			if (g.tagName && g.tagName.toLowerCase() == "hr") {
				a = d.clone();
				$(g).replaceWith(a);
				continue
			}
			a.appendChild(g)
		}
		new Element("div", {
			styles : {
				clear : "both"
			}
		}).inject(c)
	}
};
Wiki.addPageRender(WikiColumns);
var WikiPrettify = {
	render : function(c, a) {
		var b = $ES(".prettify pre, .prettify code", c);
		if (!b || b.length == 0) {
			return
		}
		b.addClass("prettyprint");
		prettyPrint(c)
	}
};
Wiki.addPageRender(WikiPrettify);